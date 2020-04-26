import unittest
from decimal import Decimal
from fractions import Fraction

# https://docs.python.org/3/library/stdtypes.html
class TestBuiltInTypes(unittest.TestCase):

    def test_TruthValueTesting(self):
        self.assertFalse(None)
        self.assertFalse(False)
        self.assertFalse(0)
        self.assertFalse(0.0)
        self.assertFalse(0j)
        self.assertFalse(Decimal(0))
        self.assertFalse(Fraction(0, 1))
        self.assertFalse('')
        self.assertFalse(())
        self.assertFalse([])
        self.assertFalse({})
        self.assertFalse(set())
        self.assertFalse(range(0))

    def test_BooleanOperations(self):
        self.assertFalse(False and False)
        self.assertFalse(False and True)
        self.assertFalse(True and False)
        self.assertTrue(True and True)
        self.assertFalse(False or False)
        self.assertTrue(False or True)
        self.assertTrue(True or False)
        self.assertTrue(True or True)
        self.assertTrue(not False)
        self.assertFalse(not True)

    def test_Comparisons(self):
        self.assertTrue(1 < 2)
        self.assertFalse(2 < 2)
        self.assertTrue(1 <= 2)
        self.assertTrue(1 <= 2)

if __name__ == '__main__':
    unittest.main()