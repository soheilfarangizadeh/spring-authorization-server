Spring Authorization Server

The main classes are OAuth2ResourceOwnerPasswordAuthenticationConverter, OAuth2ResourceOwnerPasswordAuthenticationToken
and
OAuth2ResourceOwnerPasswordAuthenticationProvider. These classes are present in SpringAuthorizationServer in package
oauth2.authentication.

All the settings are in application.properties file. Like context path, port etc. SpringAuthorizationServer is
using MYSQL database.

These are the same projects offered by Spring. I just add the Password grant type in the AuthorizationServer and Client
as Spring is not providing support
for it because of OAuth2.1 draft.

This project is just showing how you can add custom grant type in the SpringAuthorizationServer. Like in my case I added
password grant type support to use in my project. Changes
can be made according to need in the code. Right now it is using version 0.3.1 which is the latest version. Things can
be change in upcoming versions of Spring authorization
server. So if you update the version in future and have some problem then ask on the Spring forum.

This project is just for demonstration purpose to add custom grant type.

By default, SpringAuthorizationServer will run on port 8050 with context path /auth

Already an user1 with password field is populated. Login with the user.

