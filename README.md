# user-service
small service maintains user data </br>
Mostly used by 
<a href="https://github.com/JavaAdore/authorization-server"> authorization server </a> <br/>

# prerequisites
config server should be up and run<br/>
<a href="https://github.com/JavaAdore/config-server">https://github.com/JavaAdore/config-server</a> <br/>
eureka server should be up and run<br/>
<a href="https://github.com/JavaAdore/eureka-server">https://github.com/JavaAdore/eureka-server</a> <br/>

authorization server should be up and run<br/>

<a href="https://github.com/JavaAdore/authorization-server">https://github.com/JavaAdore/authorization-server</a> <br/>


zipkin server nice be up and run so you can track the request<br/>
<a href="https://github.com/JavaAdore/zipkin-server">https://github.com/JavaAdore/zipkin-server</a> <br/>


Postgres DB <br/>
RabbitMQ <br/>
Ensure rabbitMQ has exchange called "logExchange" <br/>
Ensure exchange "logExchange" has valid routing for messages with routing key "footPrint" ;<br/>
All Methods annotated by @FootPrint("EVENT NAME") pushs logs for event and it's result (event success or throwing busniness of unhandled exception) into the exchange and   <a href="https://github.com/JavaAdore/log-service">log-service</a> has listener on a queue ( the final destination of exchange logExchange with routing key footPrint and then logs the message in mongo db  <br/>


 

environment variables should be added

# ZIPKIN_SERVER_IP = 127.0.0.1
127.0.0.1 the ip of machine where zipkin server runs
# SLEUTH_LOGGING_LEVEL=info
level of sleuth loggin

# RABBITMQ_LISTENER_IP = 127.0.0.1
# RABBITMQ_LISTENER_PORT = 5672
# RABBITMQ_DEFAULT_USER = user
change user to username of rabbitmq
# RABBITMQ_DEFAULT_PASS = password
change password to username of rabbitmq


# POSTGRES_SERVER_IP    = 127.0.0.1
# POSTGRES_SERVER_PORT  = 5432
# POSTGRES_DBNAME 	    = postgres     
 
 
# AUTHORIZATION_SERVER_IP=127.0.0.1
# AUTHORIZATION_SERVER_PORT=8888

# EUREKA_SERVER_IP      = 127.0.0.1
# EUREKA_SERVER_PORT    = 8761




# user-service provides the following functionalities

Long registerNewPublicUser(UserModel userModel) throws ServiceException;<br/>

Long updateUser(Long userId  , UserModel userModel) throws ServiceException ;<br/>

Long addUserAddress(Long userId, UserAddressModel userAddress) throws ServiceException;<br/>

Long updateUserAddress(Long userId, Long userAddressId, UserAddressModel userAddress) throws ServiceException;<br/>

Long deleteUserAddress(Long userId, Long userAddressId) throws ServiceException;<br/>

List<UserAddressModel> getUserAddresses(Long userId) throws ServiceException;<br/>

Long setUserDefaultAddress(Long userId, Long userAddressId) throws ServiceException;<br/>

UserModel getUserModel(Long userId) throws ServiceException;<br/>

void changePassword(Long loginUserId, ChangePasswordModel changePasswordModel);<br/>

Long addNewSystemUser(String userName)throws ServiceException;<br/>

Long assignUserToEntity(Long userId, Long entityId)throws ServiceException;<br/>


# build
as root/Administration <br/>
mvn clean install docker:removeImage docker:build
# run
java -jar target/user-server.jar
