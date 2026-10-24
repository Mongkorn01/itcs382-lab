# Lab Report, Code Quality and Design Quality

Repository URL: https://github.com/Mongkorn01/itcs382-lab

## Tools

| Item | Version or name |
| --- | --- |
| JDK | 17.0.20.1 |
| Maven |  3.10.0 |
| IDE | VS Code |
| SonarQube for IDE | v6.1.1 |
| AI tool and exact model | |

## Task 1. SonarQube on the starter code

Commit of the starter, before any change: 7c2d9c1 (HEAD -> main, origin/main) Starter code

Table 1. SonarQube issues in the starter code

| No. | Rule | File:line | Message |
| --- | --- | --- | --- |
| 1 | sonarqube(java:S106)| Main.java:9 | Replace this use of System.out by a logger. |
| 2 | sonarqube(java:S106)| Main.java:15 | Replace this use of System.out by a logger. |
| 3 | sonarqube(java:S106)| Main.java:17 | Replace this use of System.out by a logger. |
| 4 | sonarqube(java:S106)| Main.java:22 | Replace this use of System.out by a logger. |
| 5 | sonarqube(java:S2143)| ParcelService.java:1 | Use the "java.time" API for date and time. |
| 6 | sonarqube(java:S2143)| ParcelService.java:6 | Remove this unused import 'java.util.Date'. |
| 7 | sonarqube(java:S1068) | ParcelService.java:12 | Remove this unused "maxParcels" private field. |
| 8 | sonarqube(java:S106) | ParcelService.java:30 | Replace this use of System.out by a logger. |
| 11 | sonarqube(java:S4973) | ParcelService.java:42 | Strings and Boxed types should be compared using "equals()". |
| 12 | sonarqube(java:S106) | ParcelService.java:67 | Replace this use of System.out by a logger. |
| 13 | sonarqube(java:S106) | ParcelService.java:69 | Replace this use of System.out by a logger. |
| 15 | sonarqube(java:S2095) | ParcelService.java:76 | Use try-with-resources or close this "FileWriter" in a "finally" clause. |
| 16 | sonarqube(java:S108) | ParcelService.java:80 | Remove this block of code, fill it in, or add a comment explaining why it is empty. |

## Task 2. AI review of the starter code

| Item | Value |
| --- | --- |
| Date | 19 October 2026 |
| AI tool and exact model | ChatGPT (GPT-6) |
| Commit reviewed | 7ddd72a (HEAD -> main, origin/main) Task 1 |
| How the AI read the files (attached the files, opened the folder, or pasted the files) | attached the files |
| Output file in the reviews folder | Yes (/reviews/parcelpoint-review.md)|

Table 2. All findings on the starter code

| No. | File:line | Finding | Found by (SonarQube, AI or both) | SonarQube rule or course term | Correct? (yes or no, and why) |
| --- | --- | --- | --- | --- | --- |
| 1 | `Main.java:9` | Replace this use of `System.out` by a logger. | SonarQube | `java:S106` |No, "System.out.println" works just fine.|
| 2 | `Main.java:15` | Replace this use of `System.out` by a logger. | SonarQube | `java:S106` |No, "System.out.println" works just fine.|
| 3 | `Main.java:17` | Replace this use of `System.out` by a logger. | SonarQube | `java:S106` |No, "System.out.println" works just fine.|
| 4 | `Main.java:22` | Replace this use of `System.out` by a logger. | SonarQube | `java:S106` |No, "System.out.println" works just fine.|
| 5 | `ParcelService.java:6` | Remove this unused import `java.util.Date`. | SonarQube | `java:S1128` |Yes, it is not used.|
| 6 | `ParcelService.java:12` | Remove this unused `maxParcels` private field. | Both | `java:S1068` |Yes, it is not used.|
| 7 | `ParcelService.java:30` | Replace this use of `System.out` by a logger. | SonarQube | `java:S106` |No,"System.out.println" works just fine.|
| 8 | `ParcelService.java:42` | Strings and Boxed types should be compared using `equals()`. | Both |`java:S4973` |Yes, strings compare uses equals()|
| 9 | `ParcelService.java:67` | Replace this use of `System.out` by a logger. | SonarQube | `java:S106` |No, "System.out.println" works just fine.|
| 10 | `ParcelService.java:69` | Replace this use of `System.out` by a logger. | SonarQube | `java:S106` |No, "System.out.println" works just fine.|
| 11 | `ParcelService.java:76` | Use try-with-resources or close this `FileWriter` in a `finally` clause. |Both | `java:S2095` |Yes,the file writer is not closed.|
| 12 | `ParcelService.java:80` | Remove this block of code, fill it in, or add a comment explaining why it is empty. | Both | `java:S108` |Yes, it is better if there is an explanation for errors.|
| 13 | `ParcelService.java:28,44,68` | Parcel statuses are represented by repeated string literals, making spelling inconsistencies and invalid states easier to introduce. | AI | Connascence of convention | Yes, the current version is not robust to typos|
| 14 | `ParcelService.java:30–32` | `storeParcel` combines locker allocation, parcel-state changes, notification, persistence, and returning the result. | AI | Method cohesion / Single responsibility |Yes, the functions should do only one thing.|
| 15 | `Main.java:11–17` | The command parser assumes valid input lengths and numeric arguments, so malformed commands can cause index errors or `NumberFormatException`. | AI | Input validation / Robustness |Yes, it is missing input validation.|
| 16 | `Main.java:11` | Splitting input on a single space does not robustly handle repeated spaces or empty input. | AI | Input parsing / Robustness |Yes, the program might not work as intended|
| 17 | `ParcelService.java:76–80` | `saveToFile` overwrites `parcels.csv` but silently discards write errors, so persistence failures can go unnoticed. | AI | Error handling |Yes, it overwrites the file.|
| 18 | `ParcelService.java:64–71` | `printParcels` mixes two display behaviors selected by a boolean parameter. | AI | Logical cohesion |Yes, the parameter determines the behaviour.|
| 19 | `ParcelService.java:39–48` | `pickUp` removes a parcel from its locker before calculating and returning the fee, combining state changes with billing logic. | AI | Method cohesion / Separation of concerns |Yes, a function should do only one behaviour|
| 20 | `Main.java:6–26` | `main` combines command parsing, object creation, service calls, output, and loop termination. | AI | Procedural cohesion |Yes, the behaviour cares about sequence |
| 21 | `ParcelService.java:24–36` | `storeParcel` sequences allocation, status updates, notification, persistence, and returning. | AI | Procedural cohesion | Yes, the behaviour cares about sequence |
| 22 | `ParcelService.java:64–71` | `printParcels` selects between two display operations based on `forStaff`. | AI | Logical cohesion | Yes, 2 beheavior depends on if-else|
| 23 | `ParcelService.java:9–80` | `ParcelService` combines locker allocation, parcel tracking, fee calculation, notifications, console output, and file persistence. | AI | Weak class cohesion |Yes, the class does unrelated tasks|
| 24 | `Main.java:19–22` | `Main` accesses the locker collection through `ParcelService.getLockers()` and inspects individual lockers. | AI | Coupling / Law of Demeter | Yes, it gained more information than necessary|
| 25 | `Main.java:20–21`, `ParcelService.java:64–71` | The `forStaff` boolean determines which display behavior the service performs. | AI | Control coupling |Yes, 2 different behaviours with if-else|
| 26 | `ParcelService.java:27–28,43–44,67–68`, `Parcel.java:7` | `ParcelService` directly accesses and modifies the public `Parcel.status` field. | AI | Encapsulation / Content coupling |Yes, it should not access attribute directly|
| 27 | `ParcelService.java:26–28`, `Locker.java:25–26` | `ParcelService` passes a complete `Parcel` object to `Locker`. | AI | Stamp coupling |Yes, it only uses some of the available information|
| 28 | `ParcelService.java:28,44,68`, `Parcel.java:13` | Status values depend on matching string literals across classes and methods. | AI | Connascence of convention |Yes, it uses a string with different semantics|
| 29 | `Locker.java:5`, `Parcel.java:6`, `ParcelService.java:26,56–61`, `Main.java:9` | Locker and parcel sizes use numeric codes whose meanings must remain consistent across several locations. | AI | Connascence of convention | Yes,the number to meaning must be remembered|
| 30 | `ParcelService.java:20–21` | `getLockers` returns the mutable internal collection, allowing callers to add or remove lockers. | AI | Encapsulation / Mutable collection exposure |Yes, it returns a whole list, allowing modifications.|
| 31 | `Parcel.java:7` | The `status` field is public, allowing callers to assign arbitrary values and bypass state validation. | AI | Encapsulation |Yes, it is modification if refers directly|

2.1 Name two problems that only the AI review found.
1. ParcelService's saveToFile will overwrite the csv file.
2. Main and ParcelService.java has low quality design for cohesion and coupling.

2.2 Name one problem that only SonarQube found, or write none.
1. java.util.Date was not used


2.3 Name one AI finding that was wrong or too vague, and explain why. If every finding was right, explain how you checked them.
1. Every finding was right. Most of the AI answers were design problems, so I checked the for the right level of coupling and cohesion from our lecture.

## Task 3. Fix and check

Table 3. What you did with each SonarQube issue in Table 1

| No. in Table 1 | Fixed or kept | Commit, or the reason for keeping it |
| --- | --- | --- |
| 1 | Kept | System.out.println works perfectly fine for a small console application like ParcelPoint|
| 2 | Kept | System.out.println works perfectly fine for a small console application like ParcelPoint|
| 3 | Kept | System.out.println works perfectly fine for a small console application like ParcelPoint|
| 4 | Kept | System.out.println works perfectly fine for a small console application like ParcelPoint|
| 5 | Fixed | Remove unused import java.util.Date|
| 6 | Fixed | Remove unused maxParcels|
| 7 | Kept | System.out.println works perfectly fine for a small console application like ParcelPoint|
| 8 | Fixed | Use equals() for string compare|
| 9 | Kept | System.out.println works perfectly fine for a small console application like ParcelPoint|
| 10 | Kept | System.out.println works perfectly fine for a small console application like ParcelPoint|
| 11 | Fixed | Close file writer with finally clause|
| 12 | Fixed | Add a stack trace for errors|
| 13 | Kept | The current using explicit name for convention is good enough|
| 14 | Kept | For small program, printing for debugging is ok|
| 15 | Fixed | Add input validation for command parser|
| 16 | Fixed | Handle many spaces|
| 17 | Kept | Overwrite might be the intented action |
| 18 | Kept | The current way is difficult to change due to couplings with Main.java |
| 19 | Kept | The current way is difficult to change due to couplings with Main.java |
| 20 | Kept | The sequence is necessary for the program to work correctly|
| 21 | Kept | The sequence is necessary for the program to work correctly|
| 22 | Kept | The current way is difficult to change due to couplings with Main.java |
| 23 | Kept | The current way is difficult to change due to couplings with Main.java |
| 24 | Fixed| Reduce coupling by adding countFreeLockers() |
| 25 | Kept | The current way is difficult to change due to couplings with Main.java |
| 26 | Fixed | Add get and set for status to enfore encapsulation |
| 27 | Kept | The current implementation is simple, adding changes might introduce more bugs |
| 28-29 | Fixed | Make enum for size of locker and parcel, so the name is explicit |
| 30 | Kept | The current implementation is simple, adding changes might introduce more bugs|
| 31 | Fixed | The get and set method is added on step 26|


Number of SonarQube issues that remain after your fixes: 10

The two design problems that you fixed
- Parcel and Locked size was in number instead of explicit name
- No input validation for add Locker

| Design problem (course term) | What you changed | Commit |
| --- | --- | --- |
| Connascence of convention| Replaced numeric parcel and locker sizes with a LockerSize enum (SMALL, MEDIUM, LARGE) so their meanings are explicit.| 3743803 |
| Content coupling | Added getter and setter methods for parcel status instead of accessing the field directly. |72a3556 |
|Coupling / Law of Demeter|Added countFreeLockers() to ParcelService so Main doesn't need to access the locker collection to count free lockers.|55697ce|

Output of the program after your fixes, for the commands `store P1 6501 2`, `pickup P1 5` and `staff`, and the content of parcels.csv:

```text
Commands: store <code> <studentId> <size 1-3>, pickup <code> <days>, list, staff, quit
store P1 6501 2
SMS to 6501: parcel P1 is in locker 2
Locker 2
pickup P1 5
Fee: 30.0
staff
P1 6501 PICKED_UP
Free lockers: 3
quit

parcels.csv:
P1,PICKED_UP

```

## Task 4. AI-generated poor and good designs

| Item | bikeshare-poor | bikeshare-good |
| --- | --- | --- |
| Date | 25 October 2026|  25 October 2026|
| AI tool and exact model |ChatGPT (GPT-6) |ChatGPT (GPT-6) |
| Prompt (B or C as given, or your changed prompt in full) | Write a Java 17 command-line program, built with Maven and with no external library, for the specification below. Give it a poor design on purpose. Put the menu, the fee rules, the bike records and the file saving into as few classes as possible, keep shared data in public static fields, use int codes for the member types, and pass a boolean flag that changes what a method does. Keep the code itself tidy, so close every file, handle every exception and leave no unused code. Keep the program short. Give me the pom.xml and every Java file.

Specification:
BikeShare lends bikes at three campus stations, Library, Canteen and Dorm, and each station starts with two bikes.
1. A member rents an available bike at a station by entering the member ID, the member type (student or staff) and the station.
2. The member returns the bike at any station and enters the ride time in minutes.
3. The fee is 2 baht per minute for a student and 3 baht per minute for a staff member.
4. The program prints a receipt with the bike, the minutes and the fee.
5. The program adds every finished ride to the file rides.csv.
6. The program reads commands from the keyboard until the user types quit.| Write a Java 17 command-line program, built with Maven and with no external library, for the specification below. Design it for high cohesion and low coupling. Give each class one responsibility, keep every field private, pass each method only the data it needs, use an enum for the member types, hide the file saving behind an interface, and keep the menu apart from the classes that hold the rules. Keep the code tidy and the program short. Give me the pom.xml and every Java file.

Specification:
BikeShare lends bikes at three campus stations, Library, Canteen and Dorm, and each station starts with two bikes.
1. A member rents an available bike at a station by entering the member ID, the member type (student or staff) and the station.
2. The member returns the bike at any station and enters the ride time in minutes.
3. The fee is 2 baht per minute for a student and 3 baht per minute for a staff member.
4. The program prints a receipt with the bike, the minutes and the fee.
5. The program adds every finished ride to the file rides.csv.
6. The program reads commands from the keyboard until the user types quit.|
| Builds with mvn package (yes or no) | yes|yes |
| Number of classes, interfaces, enums and records | 2 | 10 |
| SonarQube issues, total | 30 | 13 |
| SonarQube issues, by rule | 6 | 1 |
| AI review findings in Check 1 | 10 | 12 |
| AI review findings in Check 2 | 13 | 17 |
| Review output file in the reviews folder | yes | yes |

4.1 Did SonarQube report any cohesion or coupling problem in bikeshare-poor? Quote the rule and the message if it did.
1. Method cohesion: main has procedural cohesion: its actions are organized as a sequence of startup, execution, and cleanup steps.
2. Method cohesion: run has logical cohesion: it groups different command-handling operations and selects among them based on the command string.
3. Method cohesion: processRide has logical cohesion: the returning flag selects between two distinct workflows, rental and return, which perform different operations.
4. Class cohesion: BikeShare combines application-wide data, interactive input/output, rental and return rules, fee calculation, and file persistence, representing several responsibilities in one class.
5. Class cohesion: Bike exposes data fields but has no behavior for maintaining its own rental-state invariants, leaving state changes to BikeShare
6. Coupling: BikeShare directly reads and modifies Bike fields, creating content coupling between the two classes.
7. Coupling: saveRide receives an entire Bike object but uses only selected fields, creating stamp coupling through the composite object parameter.
8. Coupling: BikeShare depends on multiple unrelated APIs for console output, input, collections, filesystem access, file writing, paths, and exception handling, increasing the class's dependency surface.
9. Connascence: The meaning of memberType depends on shared integer constants and conditional logic mapping those integers to student/staff behavior and CSV text, creating connascence of type and connascence of algorithm.
10. Connascence: The persisted CSV schema depends on the positional correspondence between the header fields and the values written in each record, creating connascence of position.
11. Connascence: Command names and station names must match the strings used in the input handling, prompts, and lookup logic, creating connascence of name and connascence of convention.
12. Encapsulation: Mutable internal state is exposed through public fields, allowing external code to bypass validation and invariants.
13. The class exposes its internal mutable collection directly as a field; callers can modify the inventory without using inventory-management logic.

4.2 Did the AI review tell the two versions apart? Give one finding for bikeshare-poor that does not appear for bikeshare-good.
- Yes, for example, the CSV fields in bikeshare-poor are written without escaping commas, quotation marks, or newlines in memberId, so certain IDs can corrupt the CSV structure.

4.3 Which version would you rather extend with a new member type, such as a visitor who pays 4 baht per minute, and why?
- The good version, since the system is more robust and easier to add new  changes.

## Task 5. Reflection

5.1 What does SonarQube check, and what does it not check? Answer with examples from this lab.
- SonarQube checks for logic error, unused declaration, and surface level debuggings, but it does not check design quality and efficient error handling.

5.2 When should you not trust the AI review? Give an example from this lab.
- To trust AI review, we must check the actuak code ourselves. For example, an AI gave a design refactoring by splitting a function into many subfunctions. This maybe useful, but it maybe change the function name, which might introduce a lot of changes to other files and/or increase complexity.

5.3 What will you do differently in Project Phase 2 after this lab?
- I will thoroughly check both SonarQube review and AI reviews, as well as start early in order to have spare time for quality checks

## AI usage declaration

| AI tool and exact model | Purpose | Files or parts that it produced or changed | How you checked its output |
| --- | --- | --- | --- |
| ChatGPT (GPT-6)  | Combining tables | Task 2 | Quick look if the tables covers everything |
| ChatGPT (GPT-6)  | Debugging | Task 3 | Checks logic and if the code make sense & if the compilation is successful |
| ChatGPT (GPT-6)  | Mandatory tasks | Task 2, 4 | Checks if AI reviews make sense & if the compilation is successful |
