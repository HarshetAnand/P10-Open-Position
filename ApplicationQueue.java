//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Open Position: ApplicationQueue Class
// Course: CS 300 Spring 2022
//
// Author: Harshet Anand
// Email: hanand2@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Ahan Nair
// Partner Email: nair27@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Applications. Guarantees the
 * min-heap invariant, so that the Application at the root should have the lowest score, and
 * children always have a higher or equal score as their parent. The root of a non-empty queue is
 * always at index 0 of this array-heap.
 * 
 * @author Ahan Nair
 * @author Harshet Anand
 */
public class ApplicationQueue implements PriorityQueueADT<Application>, Iterable<Application> {
  private Application[] queue; // array min-heap of applications representing this priority queue
  private int size; // size of this priority queue

  /**
   * Creates a new empty ApplicationQueue with the given capacity
   * 
   * @param capacity Capacity of this ApplicationQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public ApplicationQueue(int capacity) throws IllegalArgumentException {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity is invalid.");
    }
    queue = new Application[capacity];
    size = 0;
  }

  /**
   * Checks whether this ApplicationQueue is empty
   * 
   * @return {@code true} if this ApplicationQueue is empty
   */
  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Returns the size of this ApplicationQueue
   * 
   * @return the size of this ApplicationQueue
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Adds the given Application to this ApplicationQueue and use the percolateUp() method to
   * maintain min-heap invariant of ApplicationQueue. Application should be compared using the
   * Application.compareTo() method.
   * 
   * 
   * @param o Application to add to this ApplicationQueue
   * @throws NullPointerException  if the given Application is null
   * @throws IllegalStateException with a descriptive error message if this ApplicationQueue is full
   */
  @Override
  public void enqueue(Application o) throws NullPointerException, IllegalStateException {
    if (o == null) {
      throw new NullPointerException("Error! The application cannot be null!");
    }
    if (queue.length == size) {
      throw new IllegalStateException("Error! The application queue is full!");
    }
    queue[size] = o;
    percolateUp(size); // TODO fix this argument
    size++;
  }

  /**
   * Removes and returns the Application at the root of this ApplicationQueue, i.e. the Application
   * with the lowest score.
   * 
   * @return the Application in this ApplicationQueue with the smallest score
   * @throws NoSuchElementException with a descriptive error message if this ApplicationQueue is
   *                                empty
   */
  @Override
  public Application dequeue() throws NoSuchElementException {
    // TODO verify that the queue is not empty
    if (size == 0) {
      throw new NoSuchElementException("Error! The application queue is empty!");
    }
    Application lowestScore = queue[0];
    queue[0] = queue[size - 1];
    queue[size - 1] = null;
    percolateDown(0);
    size--;
    // return the lowest-scoring application
    return lowestScore;
  }

  /**
   * An implementation of percolateDown() method. Restores the min-heap invariant of a given subtree
   * by percolating its root down the tree. If the element at the given index does not violate the
   * min-heap invariant (it is due before its children), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, then swap the element with the correct child and
   * continue percolating the element down the heap.
   * 
   * This method may be implemented recursively OR iteratively.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  private void percolateDown(int i) throws IndexOutOfBoundsException {
    Application rightNode;
    Application leftNode;
    Application temporaryApplication;
    int toPercolate;
    if (i >= size) {
      throw new IndexOutOfBoundsException("Error! The index is not valid!");
    }
    if ((2 * i) + 2 > queue.length) {
      return;
    }

    try {
      leftNode = queue[(2 * i) + 1];
    } catch (Exception e) {
      leftNode = null;
    }

    try {
      rightNode = queue[(2 * i) + 2];
    } catch (Exception e) {
      rightNode = null;
    }

    if (leftNode == null && rightNode != null) {
      temporaryApplication = rightNode;
      toPercolate = (2 * i) + 2;
    } else if (leftNode != null && rightNode == null) {
      temporaryApplication = leftNode;
      toPercolate = (2 * i) + 1;
    } else if (leftNode == null && rightNode == null) {
      return;
    } else {
      if (rightNode.compareTo(leftNode) < 0) {
        temporaryApplication = rightNode;
        toPercolate = (2 * i) + 2;
      } else if (leftNode.compareTo(rightNode) < 0) {
        temporaryApplication = leftNode;
        toPercolate = (2 * i) + 1;
      } else {
        temporaryApplication = leftNode;
        toPercolate = (2 * i) + 1;
      }
    }
    if (queue[i] == null) {
      return;
    }
    if (queue[i].compareTo(temporaryApplication) > 0) {
      temporaryApplication = queue[i];
      queue[i] = queue[toPercolate];
      queue[toPercolate] = temporaryApplication;
      percolateDown(toPercolate);
    }
  }


  /**
   * An implementation of percolateUp() method. Restores the min-heap invariant of the tree by
   * percolating a leaf up the tree. If the element at the given index does not violate the min-heap
   * invariant (it occurs after its parent), then this method does not modify the heap. Otherwise,
   * if there is a heap violation, swap the element with its parent and continue percolating the
   * element up the heap.
   * 
   * This method may be implemented recursively OR iteratively.
   * 
   * Feel free to add private helper methods if you need them.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  private void percolateUp(int i) {
    Application parentApplication;
    int parent = (i - 2) / 2;
    if (i == 0) {
      return;
    }
    if (queue[i].compareTo(queue[parent]) < 0) {
      parentApplication = queue[parent];
      queue[parent] = queue[i];
      queue[i] = parentApplication;
      percolateUp(parent);
    } else {
      return;
    }
  }

  /**
   * Returns the Application at the root of this ApplicationQueue, i.e. the Application with the
   * lowest score.
   * 
   * @return the Application in this ApplicationQueue with the smallest score
   * @throws NoSuchElementException if this ApplicationQueue is empty
   */
  @Override
  public Application peek() throws NoSuchElementException {
    // TODO verify that the queue is not empty
    if (this.isEmpty()) {
      throw new NoSuchElementException("Error! The application queue is empty!");
    }
    return queue[0];
  }

  /**
   * Returns a deep copy of this ApplicationQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * applications. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this ApplicationQueue. The returned new application queue has the same
   *         length and size as this queue.
   */
  public ApplicationQueue deepCopy() {
    ApplicationQueue deepCopy = new ApplicationQueue(queue.length);
    for (int i = 0; i < queue.length; i++) {
      if (queue[i] == null) {
        return deepCopy;
      }
      deepCopy.enqueue(queue[i]);
    }
    return deepCopy;
  }

  /**
   * Returns a String representing this ApplicationQueue, where each element (application) of the
   * queue is listed on a separate line, in order from the lowest score to the highest score.
   * 
   * This implementation is provided.
   * 
   * @see Application#toString()
   * @see ApplicationIterator
   * @return a String representing this ApplicationQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();

    for (Application a : this) {
      val.append(a).append("\n");
    }

    return val.toString();
  }

  /**
   * Returns an Iterator for this ApplicationQueue which proceeds from the lowest-scored to the
   * highest-scored Application in the queue.
   * 
   * This implementation is provided.
   * 
   * @see ApplicationIterator
   * @return an Iterator for this ApplicationQueue
   */
  @Override
  public Iterator<Application> iterator() {
    return new ApplicationIterator(this);
  }
}
