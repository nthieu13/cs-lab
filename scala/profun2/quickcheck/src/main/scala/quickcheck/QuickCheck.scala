package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    k <- arbitrary[A]
    v <- oneOf(const(empty), genHeap)
  } yield insert(k, v)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("insert") = forAll { (h: H) =>
    val a = arbitrary[A].sample.get
    val b = arbitrary[A].sample.get
    if (isEmpty(h) && a < b) {
      findMin(insert(b, insert(a, h))) == a
    } else {
      true
    }
  }

  property("delete") = forAll { (h: H) =>
    val a = arbitrary[A].sample.get
    if (isEmpty(h)) {
      isEmpty(deleteMin(insert(a, h)))
    } else {
      true
    }
  }

  property("meld") = forAll { (h1: H, h2: H) =>
    val a = findMin(h1)
    val b = findMin(h2)
    val m = findMin(meld(h1, h2))
    a == m || b == m
  }

  property("sort") = forAll { (h: H) =>
    def isSorted(h: H): Boolean =
      if (isEmpty(h)) true
      else {
        val m = findMin(h)
        val h2 = deleteMin(h)
        isEmpty(h2) || (m <= findMin(h2) && isSorted(h2))
      }
    isSorted(h)
  }

  property("meld2") = forAll { (h1: H, h2: H) =>
    def heapEqual(h1: H, h2: H): Boolean =
      if (isEmpty(h1) && isEmpty(h2)) true
      else {
        val m1 = findMin(h1)
        val m2 = findMin(h2)
        m1 == m2 && heapEqual(deleteMin(h1), deleteMin(h2))
      }
    heapEqual(meld(h1, h2),
      meld(deleteMin(h1), insert(findMin(h1), h2)))
  }

  property("nw1") = forAll { (h1: H, h2: H) =>
    val m1 = findMin(h1)
    val m2 = findMin(h2)
    val min = m1.min(m2)
    findMin(meld(deleteMin(h1), insert(min, h2))) == min
  }
}
