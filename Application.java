//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Open Position: Application Class
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

/**
 * This class models a application with a name and due date
 * 
 * @author Ahan Nair
 * @author Harshet Anand
 */
public class Application implements Comparable<Application> {
  private final String name; // name of this applicant
  private final String email; // email of this applicant
  private final int score; // estimated score of this applicant

  /**
   * Creates a new Application with the given information
   *
   * @param name  name of this applicant
   * @param email email of this applicant
   * @param score estimated score of this applicant (must be in the range 0 .. 100)
   * @throws IllegalArgumentException if the provided name is null or blank, or if the email is null
   *                                  or does not have a single {@literal @}, or if score is not in
   *                                  the 0 .. 100 range.
   */
  public Application(String name, String email, int score) throws IllegalArgumentException {
    // throws an IllegalArgumentException if the provided name is null or blank
    int counter = 0;
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Error! The name is empty!");
    }
    // ... or if the provided email is null, or has no or multiple @
    if (email == null) {
      throw new IllegalArgumentException("Error! The email is null!");
    }
    for (int i = 0; i < email.length(); i++) {
      if (email.charAt(i) == '@') {
        counter++;
      }
    }
    if (!(counter == 1)) {
      throw new IllegalArgumentException("Error! The email has multiple @!");
    }
    // ... or if the provided score is not in the 0 .. 100 range
    else {
      if (score < 0 || score > 100) {
        throw new IllegalArgumentException("Error! The score is invalid!");
      }
    }
    // initialize values (TODO change these)
    this.name = name;
    this.email = email;
    this.score = score;
  }


  /**
   * Returns the name of this Applicant
   * 
   * @return the name of this Applicant
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the email of this Applicant
   * 
   * @return the email of this Applicant
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Returns the score of this Applicant
   * 
   * @return the score of this Applicant
   */
  public int getScore() {
    return this.score;
  }

  /**
   * TODO: add this method Compares this Applicant to another applicant based on their score
   * 
   * @return a negative integer if this Applicant has a lower score, {@code 0} if the two Applicants
   *         have the same score, and a positive integer if this Applicant has a higher score.
   * @throws NullPointerException if the other assignment o is null
   */
  @Override
  public int compareTo(Application other) throws NullPointerException {
    if (other == null) {
      throw new NullPointerException("Error! Other is null!");
    }
    if (other.score > this.score) {
      return -1;
    } else if (other.score == this.score) {
      return 0;
    } else {
      return 1;
    }
  }

  /**
   * Returns a String representing this Application containing its name, email and score. (This
   * implementation is provided for you.)
   * 
   * @return a String representing this Application
   */
  @Override
  public String toString() {
    return name + ":" + email + ":" + score;
  }
}
