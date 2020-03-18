from unittest import TestCase
from three_sums import threeSum


class TestThreeSum(TestCase):
    def test_sum(self):
        self.assertEqual(threeSum([-1, 0, 1, 2, -1, -4]), [[-1, -1, 2]])
        self.assertEqual(threeSum([0, 0, 0, 0]), [])
        self.assertEqual(threeSum([0, 0]), [])
        self.assertEqual(threeSum([-2, 0, 1, 1, 2]), [[-2, 0, 2], [-2, 1, 1]])
        self.assertEqual(threeSum([-1, 0, 1, 0]), [[-1, 0, 1]])
