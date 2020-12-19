import sys
sys.path.append('../')
from libraries.condition import Condition

class Table:
	def __init__(self, a_class_attribute, instance_of_attributes):
		self.a_class_attribute = a_class_attribute
		self.attributes = instance_of_attributes
		self.tuples = None
		self.images = None
		self.thumbnails = None
		return

	def add(self, a_tuple):
		a_tuple.set_values([a_string.replace('\n', '') for a_string in a_tuple.get_values()])
		Condition(len(a_tuple.get_values()) > 0 and a_tuple.get_values()[0] != '').if_true(lambda: self.get_tuples().append(a_tuple))
		return

	def get_a_class_attribute(self):
		return self.a_class_attribute

	def get_attributes(self):
		return self.attributes

	def set_tuples(self, tuples):
		self.tuples = tuples

	def get_tuples(self):
		Condition(self.tuples is None).if_true(lambda: self.set_tuples([]))
		return self.tuples
