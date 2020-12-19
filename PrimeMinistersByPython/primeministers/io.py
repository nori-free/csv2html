import urllib.request

class IO:
	def __init__(self, a_table):
		self.table = a_table
		return

	def get_csv_filepath(self):
		return self.a_csv_filepath
	
	def get_table(self):
		return self.table

	def get_tuples(self):
		return self.get_table().get_tuples()

	def read_text_from_file(self, a_filepath):
		a_list = []
		with open(a_filepath, 'r') as a_file:
			a_list = [a_line for a_line in a_file]
		return a_list

	def read_text_from_url(self, a_string):
		list_of_url = []
		with urllib.request.urlopen(a_string) as response:
			for a_line in response.read().decode().split('\n'):
				list_of_url.append(a_line)
		return list_of_url

	def set_csv_filepath(self, a_filepath):
		self.a_csv_filepath = a_filepath
		return

	def write_image(self, an_url, a_file):
		with urllib.request.urlopen(an_url) as an_origin_file:
			with open(a_file, mode='wb') as a_local_file:
				a_local_file.write(an_origin_file.read())
		return a_file

	def write_text(self, list_of_csv, base_directory):
		with open(base_directory, mode='w') as a_target_file:
			for a_line in list_of_csv:
				a_target_file.write('{}\n'.format(a_line))
		return base_directory