import unittest

# https://docs.python.org/3/library/functions.html
class TestBuiltInFunctions(unittest.TestCase):

    def test_abs(self):
        self.assertEqual(abs(0), 0)
        self.assertEqual(abs(123), 123)
        self.assertEqual(abs(-123), 123)
        self.assertEqual(abs(-123.45), 123.45)

    def test_all(self):
        self.assertTrue(all([]))
        self.assertFalse(all([False]))
        self.assertTrue(all([True]))
        self.assertFalse(all([True, False]))
        self.assertTrue(all([True, True]))

    def test_any(self):
        self.assertFalse(any([]))
        self.assertFalse(any([False]))
        self.assertTrue(any([True]))
        self.assertTrue(any([True, False]))
        self.assertTrue(any([True, True]))

if __name__ == '__main__':
    unittest.main()