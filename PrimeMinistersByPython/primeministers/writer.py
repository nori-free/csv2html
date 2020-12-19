from .attributes import Attributes
import cv2
import os

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
		print('書き込みますよー')
		inner_html = ''
		formatted_list = [self.get_table_row(a_tuple.get_values()) for a_tuple in a_table.get_tuples()]
		class_name = a_table.get_a_class_attribute()
		# trの複数形
		trs = ''.join(formatted_list)
		# print(class_name, trs)
		a_html = '<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n<html lang=\"ja\">\n<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html;\n\t charset=UTF-8\">\n<meta http-equiv=\"Content-Style-Type\" content=\"text/css\">\n<meta http-equiv=\"Content-Script-Type\" content=\"text/javascript\">\n<meta name=\"keywords\" content=\"Smalltalk,Oriented,Programming\">\n<meta name=\"description\" content=\"Prime Ministers\">\n<meta name=\"author\" content=\"AOKI Atsushi\">\n<link rev=\"made\" href=\"http://www.cc.kyoto-su.ac.jp/~atsushi/\">\n<link rel=\"index\" href=\"index.html\">\n<style type=\"text/css\">\n<!--body {{background-color:#ffffff;\n\tmargin:20px;\n\tpadding:10px;\n\tfont-family:serif;\n\tfont-size:10pt;\n\t}}\na {{text-decoration:underline;\n\tcolor:#000000;\n\t}}\na:link {{background-color:#ffddbb;\n\t}}\na:visited {{background-color:#ccffcc;\n\t}}\na:hover {{background-color:#dddddd;\n\t}}\na:active {{background-color:#dddddd;\n\t}}\ndiv.belt {{background-color:#eeeeee;\n\tpadding:0px 4px;\n\t}}\ndiv.right-small {{text-align:right;\n\tfont-size:8pt;\n\t}}\nimg.borderless {{border-width:0px;\n\tvertical-align:middle;\n\t}}\ntable.belt {{border-style:solid;\n\tborder-width:0px;\n\tborder-color:#000000;\n\tbackground-color:#ffffff;\n\tpadding:0px 0px;\n\twidth:100%;\n\t}}\ntable.content {{border-style:solid;\n\tborder-width:0px;\n\tborder-color:#000000;\n\tpadding:2px 2px;\n\t}}\ntd.center-blue {{padding:2px 2px;\n\ttext-align:center;\n\tbackground-color:#ddeeff;\n\t}}\ntd.center-pink {{padding:2px 2px;\n\ttext-align:center;\n\tbackground-color:#ffddee;\n\t}}\ntd.center-yellow {{padding:2px 2px;\n\ttext-align:center;\n\tbackground-color:#ffffcc;\n\t}}\n-->\n</style>\n<title>\n{0}</title>\n</head>\n<body>\n<div class=\"belt\">\n<h2>\n{1}</h2>\n</div>\n<table class=\"belt\" summary=\"table\">\n<tbody>\n<tr>\n<td>\n<table class=\"content\" summary=\"table\">\n<tbody>\n{2}</tbody>\n</table>\n</td>\n</tr>\n</tbody>\n</table>\n<hr>\n<div class=\"right-small\">\nCreated by primeministers.Writer on 2020/09/17 at 12:38:07</div>\n</body>\n</html>\n'.format(class_name, class_name, trs)
		# print(a_html)
		with open(os.path.join(a_table.get_attributes().get_base_directory(), 'index.html'), mode='w') as a_file:
			a_file.write(a_html)
		return
