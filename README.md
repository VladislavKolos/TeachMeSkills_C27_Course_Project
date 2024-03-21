# TeachMeSkills_C27_Course_Project
Course project for TeachMeSkills

1. **Task #1. Access the program using your login and password.**
The application allows the user to log into the program by entering a login and password.
Application contains:
- Class **"Coder"** which contains private constructor which does not allow creating objects of this class, static methods **"codeLogin"**, **"codePassword"** and **"addSalt"** for login and password encoding, methods **"decodeLogin"** and **"decodePassword"** for login and password decoding;
- Class **"AuthorizationService"** which contains constructor which does not allow creating objects of this class, static method **"doAuth"** authorization in the program;
- Class **"LoginAndPasswordValidator"** which contains private constructor which does not allow creating objects of this class, static method **"checkLoginAndPassword"** for checking the validity of login and password;
- Class **"Session"** with fields **"accessToken"**, **"expDate"** and constructor with methods **"setAccessToken"** and **"setExpDate"** for setting access token and session expiration date, method **"isSessionAlive"** for checks whether the session is alive or not;
- Final class **"StorageMock"** for for storing login and password;
- Exception class **"WrongLoginAndPasswordException"** with constructor with message;
- Class **"Runner"** with method main to run the program, you are asked to enter your login and password, login and password are entered, an object of the "Session" class is created and a called method of the "AuthorizationService" class "doAuth" is assigned to it with input parameters("login" and "password") for authorization in the program and starting the session.

2. **Task #2. The program must get the path to the folder with financial documents, read information from the documents and compile statistics. It is necessary to process files only in the **".txt"** format and only for 2023. Implement various checks. All invalid files must be moved to a separate folder. The final statistics must be uploaded into a separate file.**
The program requests the path to folders with financial documents. Only files in the **".txt"** format are processed and only for the year 2023. Invalid files have been moved to a separate folder. The final statistics are recorded in a separate file. 
Application contains:
- Final class **"ChecksParsingService"** which contains private constructor which does not allow creating objects of this class, contains static method "doCheckFileProcess" - method for processing files of the "Check" type;
- Final calss **"InvoicesParsingService"** which contains private constructor which does not allow creating objects of this class, contains static method "doInvoicesFileProcess" - method for processing files of the "Invoice" type;
- Final class **"OrdersParsingService"** which contains private constructor which does not allow creating objects of this class, contains static method "doOrdersFileProcess" - method for processing files of the "Order" type;
- Final class **"TotalParsingService"** which contains private constructor which does not allow creating objects of this class, contains static method "writeFileProcess" - method for records of general summary statistics for all types of documents for 2023;
- Abstract class **"Document"** parent class for describing files;
- Final class **"Check"** child class for describing check type files;
- Final class **"Invoice"** child class for describing invoice type files;
- Final class **"Order"** child class for describing order type files;
- Interface **"PathToFile"** which contains constants equal to absolute paths to the file (to the **"finalStatistics.txt"** and to the folder **"invalid_file"**);
- Interface **"Rate"** which contains constants equal to value of the dollar against the euro("EUR"), equal to value of the dollar against the pound sterling("GBP");
- Class **"Runner"** with method "writeFileProcess" with input parameter "session" of class "TotalParsingService" which is called in this class.

3. **Task #3. Implement saving logs to a separate file. Make a division of logs: for storing general information and for storing error information.**
The program implements saving logs into separate files. Logs have been separated: for storing general information and for storing error information.
Application contains:
- Class **"Logger"** which contains private constructor which does not allow creating objects of this class, contains two static methods **"logInfo"** and **"logError"** for creating action messages and creating error messages to record them in the corresponding logs.
- Interface **"PathToFile"** which contains constants equal to absolute paths to the file (to the **"eventLog.txt"** and **"errorLog.txt"**);
- Class **"Runner"** with the **"logInfo"** method of the **"Logger"** class is called to write information to **"eventLog.txt"**.

Also the **"logInfo"** and **"logError"** methods of the **"Logger"** class are called in the **"ChecksParsingService"**, **"InvoicesParsingService"**, **"OrdersParsingService"**, **"TotalParsingService"**, **"AuthorizationService"**, **"LoginAndPasswordValidator"** and **"Session"** classes to write information to **"eventLog.txt"** and **"errorLog.txt"** respectively.
