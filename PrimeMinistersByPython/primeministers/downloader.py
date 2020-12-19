from .attributes import Attributes
from .io import IO
from .table import Table
from .reader import Reader
from .tuple import Tuple

import os
import sys
sys.path.append('../')
from libraries.condition import Condition

class Downloader(IO):
	def __init__(self, a_table):
		super(Downloader, self).__init__(a_table)
		a_classname = a_table.get_a_class_attribute()
		self.url = "{0}{1}/{2}.csv".format(self.get_table().get_attributes().base_url(), a_classname, a_classname)
		return

	def download_csv(self):
		self.get_table().get_attributes().set_base_directory()
		a_string = self.get_table().get_attributes().get_base_directory()
		a_target_filepath = '{0}/{1}.csv'.format(a_string, a_string.split('/')[-1])
		self.download_file(self.url, a_target_filepath)
		return a_target_filepath

	def download_file(self, a_target_url, a_target_filepath):
		list_of_target_url = self.read_text_from_url(a_target_url)
		a_csv_filepath = self.write_text(list_of_target_url, a_target_filepath)
		return

	def download_images(self):
		self.download_pictures(self.get_table().get_attributes().index_of_image())
		return

	def download_pictures(self, index_of_pictures):
		Condition(self.get_tuples() == None).if_then_else(
			lambda: print('Tuple is None.'),
			lambda: self.download_pictures_from_url(index_of_pictures)
		)
		return

	def download_pictures_from_url(self, index_of_pictures):
		print('Yes, tuple.')
		for a_string in map(lambda item: item.get_values_with_index(index_of_pictures), self.get_tuples()):
			a_file = os.path.join(Attributes.get_base_directory(), a_string)
			url_of_item = os.path.join('{0}{1}'.format(self.get_table().get_attributes().base_url(), Attributes.get_class_name()), a_string)
			print('[Downloading...] {0} -> {1}'.format(url_of_item, a_file))
			self.write_image(url_of_item, a_file)
			print('complete!!\n')
		return

	def download_thumbnails(self):
		self.download_pictures(self.get_table().get_attributes().index_of_thumbnail())
		return

	def perform(self):
		import os
		a_desktop_path = Attributes.desktop_path()
		a_destination_directory = os.path.join(a_desktop_path, "{0}_Python".format(self.get_table().get_a_class_attribute()))
		os.makedirs(a_destination_directory, exist_ok=True)
		os.makedirs(os.path.join(a_destination_directory, "images"), exist_ok=True)
		os.makedirs(os.path.join(a_destination_directory, "thumbnails"), exist_ok=True)
		a_csv_filepath = self.download_csv()
		a_reader = Reader(self.get_table(), a_csv_filepath, self.get_table().get_a_class_attribute())
		# ここから続き
		for an_index, a_line in enumerate(a_reader.perform()):
			Condition(an_index != 0).if_true(lambda: self.get_table().add(Tuple(a_line)))
		self.download_images()
		self.download_thumbnails()
		return self.get_tuples()
