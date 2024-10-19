                                                                          TCI Assignment
1. Assignment Introduction
2. Assignment Details
3. POST API Signature and Payload
4. GET API Signature and Payload

Assignment Introduction

You are required to implement a small Spring Boot project which has two APIS: (a) a POST API to store employee data and (b) a GET API to return those employees who are eligible to receive a bonus on a given date. 


You will be graded on the following:
Correctness: given an incoming json, the response should be correct
Use of gradle to build the project
Use of java stream apis
JPA and DB layer
Unit test
Exception Handling: error messages sent back to the API client in case of exceptions.

You are required to upload the project source on gitlab/bitbucket/github etc and share the link with us. . 



Time for the exercise: try and return this within 3 days.


Assignment Details

There are two APIs to implement:
A POST API that sends a list of employees in its payload..
See API signature and sample response in section 3. This API will store the employee data in two tables : department and employee (or a document DB if you prefer that)

A GET API that returns the list of employees that are eligible to receive a bonus on a given date. See API signature and sample response in section 4.
This API implementation will read all the employees stored in the DB and from them, return only those that are eligible to receive a bonus on that date. “Eligible” employees here means those employees who are active on the request date. The API response is organized by currency and the employees are sorted by name.




POST API Signature and Payload

POST /tci/employee-bonus

Request Payload sample: 

{
	"employees": [
		{
			"empName": "raj singh",
			"department": "accounts",
			"amount": 5000,
			"currency": "INR",
			"joiningDate": "may-20-2022",
			"exitDate": "may-20-2023"
		},
		{
			"empName": "pratap m",
			"department": "accounts",
			"amount": 3000,
			"currency": "INR",
			"joiningDate": "jan-01-2021",
			"exitDate": "may-20-2023"
		},
		{
			"empName": "sushmita lal",
			"department": "IT",
			"amount": 4000,
			"currency": "INR",
			"joiningDate": "jan-01-2021",
			"exitDate": "dec-31-2021"
		},
		{
			"empName": "sam",
			"department": "Operations",
			"amount": 2500,
			"currency": "USD",
			"joiningDate": "may-20-2022",
			"exitDate": "may-20-2023"
		},
		{
			"empName": "john",
			"department": "Operations",
			"amount": 2500,
			"currency": "USD",
			"joiningDate": "jan-20-2023",
			"exitDate": "dec-30-2024"
		},
		{
			"empName": "susan",
			"department": "IT",
			"amount": 700,
			"currency": "USD",
			"joiningDate": "jan-01-2022",
			"exitDate": "dec-31-2022"
		}
	]
}



GET API Signature and Payload

	API: GET /tci//employee-bonus?date=”may-27-2022”

(here the api is requesting for employees that are eligible to receive the bonus as on may 27 2022)

Request Payload sample: 
{
	"errorMessage": "",
	"data": [
	  {
			"currency": "INR",
			"employees": [
				{
					"empName": "pratap m",
					"amount": 3000
				},
				{
					"name": "raj singh",
					"amount": 5000
				}
			]
		},
		{
			"currency": "USD",
			"employees": [
				{
					"empName": "sam",
					"amount": 2500
				},
				{
					"empName": "susan",
					"amount": 700
				}
			]
		}
	]
}



We will test your code by first calling the POST API that will store employees in the DB and then calling the GET API that will return the filtered employee data.

