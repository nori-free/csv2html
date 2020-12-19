from .attributes import Attributes
from .table import Table
from .downloader import Downloader
class Translator:
	def __init__(self, a_class_name):
		Attributes.flush_base_directory()
		self.input_table = Table(a_class_name, Attributes(a_class_name, "input"))
		self.output_table = Table(a_class_name, Attributes(a_class_name, "output"))
		return

	def execute(self):
		a_downloader = Downloader(self.input_table)
		tuples = a_downloader.perform()
		return
