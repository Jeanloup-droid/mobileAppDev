# mobileAppDev
Mobile Application Development - TD3 - Code explanation

 - Content :

	- Explain how you ensure user is the right one starting the app

The user has to type the id corresponding to the account he wants to access to 
have access to the account.
Clicking the login button starts the accountActivity. In this class, I am using the 
Retrofit library to send a GET request to the URL returning the JSON describing all
accounts contents.
The result obtained is transleted in an arryList of accounts and the correct account
is parsed in the recyclerView widget by indexing the arrayList<Accounts> list.


	- How do you securely save user's data on your phone ?

Using the interface SharedPreferences we can save data under json format on the
application. This creates a backup that is not delete at the end of the 
application's execution. This way, we are able to update the datas of our 
SharedPreferences backup when an internet access is verified. Otherwise, an 
offline execution of the application will return the account's datas that have 
been stored in the application backup at the last execution with connexion.

	- How did you hide the API url ?



	- Screenshots of your application 


 - Issues:

When I started sending GET requests with Retrofit, I successfully accessed the 
json of the URL : 'https://60102f166c21e10017050128.mockapi.io/labbbank/accounts'
but I havn't been able to send requests to the URLs finishing by the id
'https://60102f166c21e10017050128.mockapi.io/labbbank/accounts/1'. 
(I tried any number and i did it with boths URLs 
'https://60102f166c21e10017050128.mockapi.io/labbbank/accounts' and 
'https://60102f166c21e10017050128.mockapi.io/labbbank/accounts'.

I then choosed to download the complete account file and to slect the account 
corresponding to the current id.

If I had succeded in sending requests to each of the accounts specific URL,
I would have used dynamic URL 
'https://60102f166c21e10017050128.mockapi.io/labbbank/accounts/{id}' to send
my requests and I then would have registered separatly the accounts data with the
SharedPreferences library.

I don't know if the problem is due to that but I had lots of difficulties to 
synchronize my gradle files on android studio. I reinstalled three times the
application and had lots of troubles finding the dependencies fonctionning...
