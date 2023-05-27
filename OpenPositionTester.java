//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Open Position: OpenPositionTester class
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
 * This class implements unit test methods to check the correctness of Application,
 * ApplicationIterator, ApplicationQueue and OpenPosition classes in the assignment.
 * 
 * @author Ahan Nair
 * @author Harshet Anand
 */
public class OpenPositionTester {

  /**
   * This method tests and makes use of the Application constructor, getter methods, toString() and
   * compareTo() methods.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testApplication() {
    try {
      Application testApp = new Application("tester", "test@gmail.com", 45);
    } catch (Exception e) {
      return false;
    }
    boolean nameCheck = false;
    boolean nullEmail = false;
    boolean emailWithoutAt = false;
    boolean emailWithTooManyAt = false;
    boolean wrongScore = false;

    // create an Application with invalid input:
    // Empty name
    try {
      Application failTest1 = new Application("", "test@gmail.com", 45);
    } catch (Exception e) {
      nameCheck = true;
    }

    // Null name
    try {
      Application failTest2 = new Application("Name", null, 45);
    } catch (Exception e) {
      nullEmail = true;
    }

    // No @s
    try {
      Application failTest3 = new Application("Name", "testgmail.com", 45);
    } catch (Exception e) {
      emailWithoutAt = true;
    }

    // Too many @s
    try {
      Application failTest4 = new Application("Name", "test@@gmail.com", 45);
    } catch (Exception e) {
      emailWithTooManyAt = true;
    }

    // Invalid score
    try {
      Application failTest4 = new Application("Name", "test@gmail.com", -32);
    } catch (Exception e) {
      wrongScore = true;
    }

    // Checks getter
    Application getterTest = new Application("Test", "test@gmail.com", 98);
    if (!(getterTest.getName() == "Test" && getterTest.getEmail() == "test@gmail.com"
        && getterTest.getScore() == 98)) {
      System.out.println("cat");
      return false;
    }

    // Checks compareTo
    Application compareOne = new Application("Test", "test@gmail.com", 76);
    Application compareTwo = new Application("Test", "test@gmail.com", 56);
    if (compareOne.compareTo(compareTwo) != 1) {
      return false;
    }

    // Checks toString
    Application stringTest = new Application("Tester", "test@gmail.com", 76);
    String toString = "Tester:test@gmail.com:76";
    if (!(stringTest.toString().equals(toString))) {
      return false;
    }
    if (nameCheck && nullEmail && emailWithoutAt && emailWithTooManyAt && wrongScore) {
      return true;
    }
    return false;
  }

  /**
   * This method tests and makes use of the ApplicationIterator class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testApplicationIterator() {
    // create an ApplicationQueue with capacity at least 3
    ApplicationQueue test = new ApplicationQueue(4);
    // and at least 3 Applications with different scores
    Application test1 = new Application("Youtube", "youtube@gmail.com", 100);
    Application test2 = new Application("Maps", "maps@gmail.com", 86);
    Application test3 = new Application("Instagram", "Instagrame@gmail.com", 10);
    test.enqueue(test1);
    test.enqueue(test2);
    test.enqueue(test3);

    // add those Applications to the queue
    Iterator<Application> iterator = test.iterator();
    if (iterator.next().compareTo(test3) != 0) {
      return false;
    }
    if (iterator.next().compareTo(test2) != 0) {
      return false;
    }
    if (iterator.next().compareTo(test1) != 0) {
      return false;
    }
    return true;
  }

  /**
   * This method tests and makes use of the enqueue() and dequeue() methods in the ApplicationQueue
   * class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testEnqueueDequeue() {
    // create an ApplicationQueue with capacity 3
    ApplicationQueue testQueue = new ApplicationQueue(3);
    // and at least 4 Applications with different scores
    Application testOne = new Application("Instagram", "insta@gmail.com", 97);
    Application testTwo = new Application("Snapchat", "snap@gmail.com", 87);
    Application testThree = new Application("GPSLocator", "GPS@gmail.com", 30);
    Application testFour = new Application("stopwatch", "Stopwatch@gmail.com", 14);
    try {
      testQueue.enqueue(null);
      return false;
    } catch (NullPointerException e) {
    }
    try {
      testQueue.enqueue(testOne);
    } catch (Exception e) {
      return false;
    }
    try {
      testQueue.enqueue(testTwo);
      testQueue.enqueue(testThree);
    } catch (Exception e) {
      return false;
    }
    try {
      testQueue.enqueue(testFour);
    } catch (IllegalStateException e) {

    } catch (Exception e) {
      return false;
    }
    try {
      Application testDequeue = testQueue.dequeue();
      if (testDequeue.compareTo(testThree) != 0) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    try {
      if (testQueue.dequeue().compareTo(testTwo) != 0) {
        return false;
      }
      if (testQueue.dequeue().compareTo(testOne) != 0) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }

    try {
      testQueue.dequeue();
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * This method tests and makes use of the common methods (isEmpty(), size(), peek()) in the
   * ApplicationQueue class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testCommonMethods() {
    try {
      ApplicationQueue testCap = new ApplicationQueue(0);
      return false;
    } catch (IllegalArgumentException e) {
    } catch (Exception e) {
      return false;
    }

    // create an ApplicationQueue with capacity 3
    ApplicationQueue testQueue = new ApplicationQueue(3);
    // and at least 3 Applications with different scores
    Application testOne = new Application("Instagram", "insta@gmail.com", 97);
    Application testTwo = new Application("Snapchat", "snap@gmail.com", 87);
    Application testThree = new Application("GPSLocator", "GPS@gmail.com", 30);

    // verify the methods' behaviors on an empty queue
    try {
      testQueue.peek();
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      return false;
    }

    // add one Application and verify the methods' behaviors
    testQueue.enqueue(testOne);
    if (testQueue.size() != 1 || testQueue.isEmpty() || testQueue.peek().compareTo(testOne) != 0) {
      return false;
    }

    // add the rest of the Applications and verify the methods' behaviors
    testQueue.enqueue(testTwo);
    testQueue.enqueue(testThree);
    if (testQueue.isEmpty() || testQueue.size() != 3
        || testQueue.peek().compareTo(testThree) != 0) {
      return false;
    }
    return true;

  }

  /**
   * This method tests and makes use of OpenPosition class.
   * 
   * @return true when this test verifies the functionality, and false otherwise
   */
  public static boolean testOpenPosition() {
    // create an OpenPosition with 0 capacity (should fail)
    try {
      OpenPosition test1 = new OpenPosition("Janitor", 0);
      return false;
    } catch (IllegalArgumentException e) {

    } catch (Exception e) {
      return false;
    }

    // create an OpenPosition with capacity 3
    OpenPosition positionOpened = new OpenPosition("Example", 3);
    // and at least 5 Applications with different scores
    Application test1 = new Application("first", "first@gmail.com", 100);
    Application test2 = new Application("second", "second@gmail.com", 90);
    Application test3 = new Application("third", "third@gmail.com", 85);
    Application test4 = new Application("fourth", "fourth@gmail.com", 77);
    Application test5 = new Application("fifth", "fifth@gmail.com", 16);

    // verify that the 3 MIDDLE-scoring Applications can be added
    // don't use the highest and lowest scoring applications YET
    if (!positionOpened.add(test2)) {
      return false;
    }
    if (!positionOpened.add(test3)) {
      return false;
    }
    if (!positionOpened.add(test4)) {
      return false;
    }

    // verify that getApplications returns the correct value for your input
    String applicationString = "fourth:fourth@gmail.com:77\n" + "third:third@gmail.com:85\n"
        + "second:second@gmail.com:90\n";
    if (!positionOpened.getApplications().equals(applicationString)) {
      return false;
    }

    // verify that the result of getTotalScore is the sum of all 3 Application scores
    if (positionOpened.getTotalScore() != 252) {
      return false;
    }

    // verify that the lowest-scoring application is NOT added to the OpenPosition
    if (positionOpened.add(test5) || !positionOpened.getApplications().equals(applicationString)) {
      return false;
    }

    // verify that the highest-scoring application IS added to the OpenPosition
    if (!positionOpened.add(test1)) {
      return false;
    }
    return true;
  }

  /**
   * This method calls all the test methods defined and implemented in your OpenPositionTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    return testApplication() && testApplicationIterator() && testEnqueueDequeue()
        && testCommonMethods() && testOpenPosition();
  }

  /**
   * Driver method defined in this OpenPositionTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    System.out.print(runAllTests());
  }
}
