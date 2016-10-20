1. User can use UI version or no-UI version to run the solution
2. Run the AggregationApp for no-UI version and AggregationUI for UI version.
3. UI version only supports aggregating 5 test files in /input folder and generates corresponding output files in /output folder. If user wants to test specific file, can set the resource file location in no-UI version or directly copy the content to one of the 5 testing input files.
4. The validation on grade+number by default is: the first letter should be either 'k' or 'K' or 1-8, the next 4 digits should all be number and total length is 5. User can set the pattern in driver to change the validation rules.
5. All the Unit tests are under test folder. Can run them separately or directly run the AllTests for all of them.
6. The ValidationTest is for all the grade+number format tests, then we don't need to do more test in the StudentAggregationDriverErrorTest
7. Demo.log under the root folder is the log file of the solution. It has maximum size 10MB.