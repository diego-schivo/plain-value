import unittest

# https://docs.python.org/3/library/constants.html
class TestBuiltInConstants(unittest.TestCase):

    def test_False(self):
        self.assertFalse(False)

    def test_True(self):
        self.assertTrue(True)

    def test_None(self):
        self.assertIsNone(None)

if __name__ == '__main__':
    unittest.main()