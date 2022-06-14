# chat-server

To clone project: https://github.com/dostonbokhodirov/chat-server.git

Before run project you should do some the following changes to code:
1. Set active profile using `application.yml`
2. Change `PostgreSQL` database credentials in `application-dev.yml` or `application-prod.yml` files
3. Run `Redis` in your operating system (it will be needed for caching)
4. To use `FileController` in the project, you have got account in AWS for accessing AWS S3 (it uses for storing files in cloud storage). But it is additional, because this **doesn't affect** project running environment.

After that, you can use the project, and you will enjoy it :)

Deployed project link: http://50.116.20.197:8080/swagger-ui/index.html#/ **(All APIs' with Swagger UI)**
