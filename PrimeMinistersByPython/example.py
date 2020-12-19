from primeministers.translator import Translator
class Example:
	def __init__(self):
		for a_class_name in ["PrimeMinisters", "TokugawaShogunate"]:
			a_translator = Translator(a_class_name)
			a_translator.execute()
		return

def main():
	an_example = Example()
	return

if __name__ == "__main__":
	import sys
	sys.exit(main())
