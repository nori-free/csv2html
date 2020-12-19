from .attributes import Attributes
from .table import Table
from .downloader import Downloader
from .tuple import Tuple
from .writer import Writer

import datetime
import imghdr
import re as regular_expression
import os
import sys
sys.path.append('../')
from libraries.condition import Condition

class Translator:
	def __init__(self, a_class_name):
		Attributes.flush_base_directory()
		self.input_table = Table(a_class_name, Attributes(a_class_name, "input"))
		self.output_table = Table(a_class_name, Attributes(a_class_name, "output"))
		return

	def compute_number_of_days(self, a_tuple):
		index_of_period = self.input_table.get_attributes().index_of_period()
		dates = a_tuple.get_values()[index_of_period].split('〜')
		get_calendar = lambda a_bool, a_date: list(filter(a_bool, regular_expression.split('[年 | 月 | 日]', a_date)))
		splitted_start_dates = list(map(lambda a_string: int(a_string), get_calendar(lambda a_string: a_string != '', a_date = dates[0])))
		start_date = datetime.datetime(year=splitted_start_dates[0], month = splitted_start_dates[1], day=splitted_start_dates[2])
		Condition(dates[-1] != '').if_then_else(
			lambda: a_tuple.add(index_of_period + 1, (self.get_datetime(dates, get_calendar) - start_date).days + 1),
			lambda: a_tuple.add(index_of_period + 1, (datetime.datetime.today() - start_date).days + 1)
		)
		return a_tuple

	def execute(self):
		a_downloader = Downloader(self.input_table)
		tuples = a_downloader.perform()
		header = [an_item.name for an_item in self.output_table.get_attributes().get_enum()]
		tuples.insert(0, Tuple(header))
		a_writer = Writer(self.output_table)
		is_image = lambda a_column: str(a_column).startswith('images')
		is_thumbnail = lambda a_column: str(a_column).startswith('thumbnails')
		for an_index, a_tuple in enumerate(tuples):
			formatted_list = []
			a_dictionary = {}
			Condition(an_index == 0).if_then_else(
				lambda: [ formatted_list.append(a_writer.table_header(a_string)) for a_string in a_tuple.get_values() ],
				lambda: [ Condition(
					is_image(a_column) or is_thumbnail(a_column)
					).if_then_else(
					lambda: [
						Condition(is_image(a_column)).if_true(
							lambda: a_dictionary.update({"images": a_column})
						),
						Condition(is_thumbnail(a_column)).if_true(
							lambda: a_dictionary.update({"thumbnails": a_column})
						),
						Condition('thumbnails' in a_dictionary and 'images' in a_dictionary).if_true(
							lambda: Condition(an_index % 2 == 0).if_then_else(
								lambda: formatted_list.append(a_writer.get_image_with_yellow(a_dictionary)),
								lambda: formatted_list.append(a_writer.get_image_with_blue(a_dictionary))
							)
						)
						],
					lambda: Condition(an_index % 2 == 0).if_then_else(
						lambda: formatted_list.append(a_writer.get_table_data_with_yellow(a_column)),
						lambda: formatted_list.append(a_writer.get_table_data_with_blue(a_column))
					)
				) for a_column in self.compute_number_of_days(a_tuple).get_values() ]
			)
			self.output_table.add(Tuple(formatted_list))
		a_writer.write(self.output_table)
		return
	
	def get_datetime(self, dates, get_calendar):
		year, month, day = tuple(map(lambda a_string: int(a_string), get_calendar(lambda a_string: a_string != '', dates[1])))
		return datetime.datetime(year, month, day)

	def make_table_header(self, formatted_list, a_tuple):
		for a_string in a_tuple.get_values():
			formatted_list.append(a_writer.table_header(a_string))
		return formatted_list