import os
import sys
sys.path.append('../')
from libraries.condition import Condition
from enum import Enum

class PrimeMinistersInputEnum(Enum):
	No = 0
	Order = 1
	Name = 2
	Kana = 3
	Period = 4
	School = 5
	Party = 6
	Place = 7
	Image = 8
	Thumbnail = 9

	@classmethod
	def value_of(this_class, a_string):
		result = None
		for a_key in PrimeMinistersInputEnum:
			result = Condition(a_key.value == a_string).if_true_with_returns(lambda: a_key)
		Condition(result is None).if_true(lambda: lambda: (_ for _ in ()).throw(Exception('{}は有効な値ではありません'.format(a_string))))
		return result

class PrimeMinistersOutputEnum(Enum):
	人目 = 0
	代 = 1
	氏名 = 2
	ふりがな = 3
	在位期間 = 4
	在位日数 = 5
	出身校 = 6
	政党 = 7
	出身地 = 8
	画像 = 9

	@classmethod
	def value_of(this_class, a_string):
		result = None
		for a_key in PrimeMinistersOutputEnum:
			result = Condition(a_key.value == a_string).if_true_with_returns(lambda: a_key)
		Condition(result is None).if_true(lambda: lambda: (_ for _ in ()).throw(Exception('{}は有効な値ではありません'.format(a_string))))
		return result

class TokugawaShogunateInputEnum(Enum):
	No = 0
	Name = 1
	Kana = 2
	Period = 3
	Family = 4
	Rank = 5
	Image = 6
	Thumbnail = 7
	Former = 8
	Cemetery = 9

	@classmethod
	def value_of(this_class, a_string):
		result = None
		for a_key in TokugawaShogunateInputEnum:
			result = Condition(a_key.value == a_string).if_true_with_returns(lambda: a_key)
		Condition(result is None).if_true(lambda: (_ for _ in ()).throw(Exception('{}は有効な値ではありません'.format(a_string))))
		return result

class TokugawaShogunateOutputEnum(Enum):
	代 = 0
	氏名 = 1
	ふりがな = 2
	在位期間 = 3
	在位日数 = 4
	出身家 = 5
	官位 = 6
	画像 = 7
	院号 = 8
	墓所 = 9

	@classmethod
	def value_of(this_class, a_string):
		result = None
		for a_key in TokugawaShogunateInputEnum:
			result = Condition(a_key.value == a_string).if_true_with_returns(lambda: a_key)
		Condition(result is None).if_true(lambda: (_ for _ in ()).throw(Exception('{}は有効な値ではありません'.format(a_string))))
		return result

class Attributes:
	def __init__(self, a_class_name, a_string):
		self.base_directory = None
		self.keys = []
		self.names = []
		self.mode = a_string
		self.Prime_ministers_input_enum = Enum('Prime_ministers_input_enum', [
			'No',
			'Order',
			'Name',
			'Kana',
			'Period',
			'School',
			'Party',
			'Place',
			'Image',
			'Thumbnail'
		], start=0)
		self.Prime_ministers_output_enum = Enum('Prime_ministers_output_enum', [
			'人目',
			'代',
			'氏名',
			'ふりがな',
			'在位期間',
			'在位日数',
			'出身校',
			'政党',
			'出身地',
			'画像'
			], start=0)
		self.Tokugawa_shogunate_input_enum = Enum('Tokugawa_shogunate_input_enum', [
			'No',
			'Name',
			'Kana',
			'Period',
			'Family',
			'Rank',
			'Image',
			'Thumbnail',
			'Former',
			'Cemetery'
		], start=0)
		self.Tokugawa_shogunate_output_enum = Enum('Tokugawa_shogunate_output_enum', [
			'代',
			'氏名',
			'ふりがな',
			'在位期間',
			'在位日数',
			'出身家',
			'官位',
			'画像',
			'院号',
			'墓所'
		], start=0)
		Attributes.a_class_name = a_class_name
		is_primeminister = self.is_primeministers()
		is_tokugawa = self.is_tokugawa_shogunate()
		is_primeminister.if_true(lambda: self.primeministers_constructor())
		is_tokugawa.if_true(lambda: self.tokugawa_shogunate_constructor())
		return

	@staticmethod
	def get_base_directory():
		return Attributes.base_directory

	def base_url(self):
		return "http://www.cc.kyoto-su.ac.jp/~atsushi/Programs/VisualWorks/CSV2HTML/"

	@staticmethod
	def get_class_name():
		return Attributes.a_class_name

	@staticmethod
	def desktop_path():
		return os.path.join(os.environ['HOME'], "Desktop")

	@staticmethod
	def flush_base_directory():
		Attributes.base_directory = None
		return

	def index_of(self, a_string):
		# print(self.keys, a_string)
		# 重複していないと信頼
		return self.keys.index(a_string)

	def index_of_days(self):
		return self.index_of('Days')

	def index_of_image(self):
		return self.index_of('Image')

	def index_of_kana(self):
		return self.index_of('Kana')

	def index_of_name(self):
		return self.index_of('Name')

	def index_of_no(self):
		return self.index_of('No')
		
	def index_of_period(self):
		return self.index_of('Period')

	def index_of_thumbnail(self):
		return self.index_of('Thumbnail')

	def is_input_mode(self):
		return Condition(self.mode == "input")

	def is_output_mode(self):
		return Condition(self.mode == "output")

	def is_primeministers(self):
		return Condition(Attributes.a_class_name == "PrimeMinisters")

	def is_tokugawa_shogunate(self):
		return Condition(Attributes.a_class_name == "TokugawaShogunate")

	def primeministers_constructor(self):
		self.is_input_mode().if_true(lambda: [print("総理大臣 input"), self.set_keys(PrimeMinistersInputEnum)])
		self.is_output_mode().if_true(lambda: [print("総理大臣 output"), self.set_keys(PrimeMinistersOutputEnum)])
		return

	def set_base_directory(self):
		is_primeminister = self.is_primeministers()
		is_tokugawa = self.is_tokugawa_shogunate()

		is_primeminister.if_true(lambda: self.set_base_directory_with_filepath(os.path.join(self.desktop_path(), "PrimeMinisters_Python")))
		is_tokugawa.if_true(lambda: self.set_base_directory_with_filepath(os.path.join(self.desktop_path(), "TokugawaShogunate_Python")))
		return

	# def set_base_directory(self):
	# 	is_primeminister = self.is_primeministers()
	# 	is_tokugawa = self.is_tokugawa_shogunate()
	#
	# 	is_primeminister.if_true()
	# 	is_tokugawa.if_true()
	# 		pass

	def set_base_directory_with_filepath(self, a_path):
		Attributes.base_directory = a_path
		return

	def set_keys(self, an_enum):
		for a_key in an_enum:
			self.keys.append(a_key.name)
		return

	def tokugawa_shogunate_constructor(self):
		self.is_input_mode().if_true(lambda: [print("徳川幕府 input"), self.set_keys(TokugawaShogunateInputEnum)])
		self.is_output_mode().if_true(lambda: [print("徳川幕府 output"), self.set_keys(self.Tokugawa_shogunate_output_enum)])
		return
