#!/usr/bin/env python
# -*- coding: utf-8 -*-
 
"""
ソースコードディストリビューション（sdist）のための設え（しつらえ：setup）です。
$ python setup.py sdist
"""
 
__author__ = 'KISHI Noriki'
__version__ = '1.0.0'
__date__ = '2020/12/1?'
 
from distutils.core import setup
import os
import platform
import re as regular_expression
 
setup( \
    name=regular_expression.sub(r"\-[0-9]+\.[0-9]+\.[0-9]+$", "", os.path.basename(os.getcwd())), \
    version=__version__, \
    description='Example written by Python ' + ('.'.join(platform.python_version_tuple())), \
    # url='', \
    author=__author__, \
    author_email='g1744366@cc.kyoto-su.ac.jp', \
    license='The BSD 2-Clause License', \
    long_description='このプログラムは総理大臣と徳川幕府のcsvをhtmlファイルに変換するプログラムである。', \
    platforms='macOS ' + platform.mac_ver()[0], \
    packages=['jp'], \
)