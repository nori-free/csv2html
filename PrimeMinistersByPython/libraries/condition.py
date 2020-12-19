#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
条件式メッセージングプログラム：if, elseなどの条件式をオブジェクト指向らしく描こうではないかというプログラムです。
"""

__author__ = 'KISHI Noriki'
__version__ = '1.0.0'
__date__ = '2020/12/19'

class Condition:
	def __init__(self, a_got_condition):
		"""
		コンストラクタ
		"""
		self.a_condition = None
		self.a_condition = bool(a_got_condition)
		return
	
	def bool_value(self):
		return bool(self.a_condition)

	def if_false(self, a_lambda):
		"""
		条件が偽の時に実行する処理
		"""
		self.if_then_else(lambda: [], a_lambda)
		return

	def if_false_with_returns(self, a_lambda):
		"""
		条件が偽の時に実行する処理
		"""
		return self.if_then_else_with_returns(lambda: [], a_lambda)

	def if_true(self, a_lambda):
		"""
		条件が真の時に実行する処理
		"""
		self.if_then_else(a_lambda, lambda: [])
		return
		
	def if_true_with_returns(self, a_lambda):
		"""
		条件が真の時に実行する処理
		"""
		return self.if_then_else_with_returns(a_lambda, lambda: [])

	def if_then_else(self, a_lambda, another_lambda):
		"""
		条件が正のときに実行する処理と偽のときに実行する処理
		"""
		[another_lambda, a_lambda][self.a_condition]()

	def if_then_else_with_returns(self, a_lambda, another_lambda):
		"""
		条件が正のときに実行する処理と偽のときに実行する処理
		"""
		return [another_lambda, a_lambda][self.a_condition]()
