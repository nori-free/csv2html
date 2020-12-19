from .io import IO

class Reader(IO):
	def __init__(self, a_table, a_csv_filepath, a_classname):
		super(Reader, self).__init__(a_table)
		self.set_csv_filepath(a_csv_filepath)
		self.a_classname = a_classname
		return

	def perform(self):
		# print(self.read_text_from_file(self.get_csv_filepath()))
		# for a_line in self.read_text_from_file(self.get_csv_filepath()):
		# 	print(a_line.split(','))
		return [ a_line.split(',') for a_line in self.read_text_from_file(self.get_csv_filepath())]