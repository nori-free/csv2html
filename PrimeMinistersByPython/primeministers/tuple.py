class Tuple:
	def __init__(self, value_collection):
		self.values = value_collection
		return

	# def get_attributes(self):
	# 	return self.attributes

	def add(self, an_index, a_string):
		self.get_values().add(an_index, a_string)

	def get_values_with_index(self, an_index):
		return self.get_values()[an_index]

	def get_values(self):
		return self.values
	
	def set_values(self, values):
		self.values = values