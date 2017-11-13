# DevChallenge

Develop a spring boot server side API that satisfies the following acceptance criteria:
1.	A Retail Manager (using a RESTful client e.g. Chrome's Postman),
	can post a JSON payload to a shops endpoint containing details of the
	shop they want to add:
		shopName
		shopAddress.number
		shopAddress.postCode
	The server side API uses the Google Maps Geocoding API to retrieve
	the longitude and latitude for the provided shopAddress

	The server side API then stores details of the shop to an in-memory
	array of shops. Details should contain:
		shopName
		shopAddress.number
		shopAddress.postCode
		shopLongitude
		shopLatitude

2.	A Customer (using a RESTful client e.g. Chrome's Postman), can get
	a JSON payload from the shops endpoint containing details of the
	shop nearest to them:
		shopName
		shopAddress.number
		shopAddress.postCode
		shopLongitude
		shopLatitude
	The server side API accepts two URL parameters for the request:
		customerLongitude
		customerLatitude
	The server side API determines the nearest shop using the Customer’s
	longitude and latitude.