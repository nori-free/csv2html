from .attributes import Attributes
import cv2
import os

import sys
sys.path.append('../')
from libraries.condition import Condition

class Writer:
	def __init__(self, a_table):
		self.table = a_table
		return
	
	def calc_image_data(self, a_map):
		current_filename = os.path.basename(a_map['thumbnails'])
		image_number = int(os.path.splitext(current_filename)[0])
		height = width = 64
		a_filepath = os.path.join(Attributes.get_base_directory(), a_map['thumbnails'])
		height, width, _ = cv2.imread(a_filepath).shape
		return image_number, a_map['images'], a_map['thumbnails'], width, height, current_filename

	def get_image_with_blue(self, a_map):
		image_number, images, thumbnails, width, height, current_filename = self.calc_image_data(a_map)

		return self.get_table_data_with_blue(
			'<a name={0} href="{1}">\n<img class="borderless" src="{2}" width="{3}" height="{4}" alt="{5}">\n</a>\n'.format(
				image_number, images, thumbnails, width, height, current_filename
			))
	
	def get_image_with_yellow(self, a_map):
		image_number, images, thumbnails, width, height, current_filename = self.calc_image_data(a_map)

		return self.get_table_data_with_yellow(
			'<a name={0} href="{1}">\n<img class="borderless" src="{2}" width="{3}" height="{4}" alt="{5}">\n</a>\n'.format(
				image_number, images, thumbnails, width, height, current_filename
			))
	
	def get_table(self):
		return self.table
	
	def get_table_data_with_blue(self, a_string):
		return '<td class="center-blue">{}</td>\n'.format(a_string)
		
	def get_table_data_with_yellow(self, a_string):
		return '<td class="center-yellow">{}</td>\n'.format(a_string)
	
	def get_table_row(self, a_list):
		return '<tr>{}</tr>\n'.format(''.join(a_list))

	def table_header(self, a_string):
		return '<td class="center-pink">{}</td>\n'.format(a_string)

	def write(self, a_table):
		inner_html = ''
		formatted_list = [self.get_table_row(a_tuple.get_values()) for a_tuple in a_table.get_tuples()]
		# おまけ始まり
		color = Condition(len(formatted_list) % 2 == 0).if_then_else_with_returns(
			lambda: 'yellow',
			lambda: 'blue',
		)
		wrap_td = lambda a_string: Condition(len(formatted_list) % 2 == 0).if_then_else_with_returns(
			lambda: self.get_table_data_with_yellow(a_string),
			lambda: self.get_table_data_with_blue(a_string)
		)
		Condition(self.get_table().get_attributes().is_primeministers().bool_value()).if_true(
			lambda: formatted_list.append("<tr>{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}</tr>".format(
				wrap_td('64'),
				wrap_td('100-'),
				wrap_td('青木 淳'),
				wrap_td('あおき あつし'),
				wrap_td('2020年12月25日〜'),
				wrap_td('1'),
				wrap_td('近畿大学 大学院 化学研究科'),
				wrap_td('第二実験室党'),
				wrap_td('新潟県'),
				'<td class="center-{}"><a name="aoki" href="https://www.kyoto-su.ac.jp/faculty/professors/ise/s1gk4u000000aiqa-img/th_289_aoki.jpg">\n<img class="borderless" src="https://www.kyoto-su.ac.jp/faculty/professors/ise/s1gk4u000000aiqa-img/th_289_aoki.jpg" width="25" height="32" alt="super-programmer">\n</a>\n</td>'.format(color)
			))
		)
		Condition(self.get_table().get_attributes().is_tokugawa_shogunate().bool_value()).if_true(
			lambda: formatted_list.insert(1, "<tr>{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}</tr>".format(
				wrap_td('0'),
				wrap_td('青木 淳'),
				wrap_td('あおき あつし'),
				wrap_td('1600年11月11日〜1603年2月11日'),
				wrap_td('823'),
				wrap_td('青木家'),
				wrap_td('京都産業大学 教授'),
				'<td class="center-{}"><a name="aoki" href="https://www.kyoto-su.ac.jp/faculty/professors/ise/s1gk4u000000aiqa-img/th_289_aoki.jpg">\n<img class="borderless" src="https://www.kyoto-su.ac.jp/faculty/professors/ise/s1gk4u000000aiqa-img/th_289_aoki.jpg" width="64" height="64" alt="super-programmer">\n</a>\n</td>'.format(color),
				wrap_td('大学院'),
				wrap_td('第二実験室棟 65研究室')
			))
		)
		# おまけ終わり
		class_name = a_table.get_a_class_attribute()
		# trの複数形
		trs = ''.join(formatted_list)
		belt_value = Condition(class_name == 'PrimeMinisters').if_then_else_with_returns(
			lambda: '総理大臣',
			lambda: Condition(class_name == 'TokugawaShogunate').if_true_with_returns(
				lambda: '徳川幕府'
			)
		)
		a_html = '<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n<html lang=\"ja\">\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html;\n\t charset=UTF-8\">\n<meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\n<meta http-equiv=\"Content-Script-Type\" content=\"text/javascript\">\n<meta name=\"keywords\" content=\"Smalltalk,Oriented,Programming\">\n<meta name=\"description\" content=\"{0}\">\n<meta name=\"author\" content=\"AOKI Atsushi, KISHI Noriki\">\n<link rev=\"made\" href=\"http://www.cc.kyoto-su.ac.jp/~atsushi/\">\n<link rel=\"index\" href=\"index.html\">\n<style type=\"text/css\">\n<!--body {{background-color:#ffffff;\n\tmargin:20px;\n\tpadding:10px;\n\tfont-family:serif;\n\tfont-size:10pt;\n\t}}\na {{text-decoration:underline;\n\tcolor:#000000;\n\t}}\na:link {{background-color:#ffddbb;\n\t}}\na:visited {{background-color:#ccffcc;\n\t}}\na:hover {{background-color:#dddddd;\n\t}}\na:active {{background-color:#dddddd;\n\t}}\ndiv.belt {{background-color:#eeeeee;\n\tpadding:0px 4px;\n\t}}\ndiv.right-small {{text-align:right;\n\tfont-size:8pt;\n\t}}\nimg.borderless {{border-width:0px;\n\tvertical-align:middle;\n\t}}\ntable.belt {{border-style:solid;\n\tborder-width:0px;\n\tborder-color:#000000;\n\tbackground-color:#ffffff;\n\tpadding:0px 0px;\n\twidth:100%;\n\t}}\ntable.content {{border-style:solid;\n\tborder-width:0px;\n\tborder-color:#000000;\n\tpadding:2px 2px;\n\t}}\ntd.center-blue {{padding:2px 2px;\n\ttext-align:center;\n\tbackground-color:#ddeeff;\n\t}}\ntd.center-pink {{padding:2px 2px;\n\ttext-align:center;\n\tbackground-color:#ffddee;\n\t}}\ntd.center-yellow {{padding:2px 2px;\n\ttext-align:center;\n\tbackground-color:#ffffcc;\n\t}}\n-->\n</style>\n<title>\n{1}</title>\n</head>\n<body>\n<div class=\"belt\">\n<h2>\n{2}</h2>\n</div>\n<table class=\"belt\" summary=\"table\">\n<tbody>\n<tr>\n<td>\n<table class=\"content\" summary=\"table\">\n<tbody>\n{3}</tbody>\n</table>\n</td>\n</tr>\n</tbody>\n</table>\n<hr>\n<div class=\"right-small\">\nCreated by primeministers.Writer on 2020/09/17 at 12:38:07</div>\n</body>\n</html>\n'.format(class_name, class_name, belt_value, trs)
		with open(os.path.join(a_table.get_attributes().get_base_directory(), 'index.html'), mode='w') as a_file:
			a_file.write(a_html)
		return
