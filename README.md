<h1 style="color:#2be382">Element</h1>

<h2 style="color:#2be382">What is it?</h2>
<hr>
<p><span style="color:#2be382">Element</span> is a simple messenger whose purpose is the instant exchange of text messages, 
photos, pictures, videos, documents with friends, relatives, acquaintances, colleagues at work or 
at school.</p>

<h2 style="color:#2be382">Technologies</h2>
<hr>
<ul>
    <li>Spring Boot</li>
    <li>PostgreSQL</li>
    <li>Spring Data JPA</li>
    <li>Flyway</li>
    <li>Spring MVC</li>
    <ul>
        <li>HTML Pages</li>
        <li>Thymeleaf</li>
    </ul>
    <li>Spring Security</li>
    <ul>
        <li>Authentication</li>
        <li>Authorization</li>
        <li>Password hashing</li>
    </ul>
    <li>Docker</li>
    <li>Unit testing</li>
    <li>Other libraries and frameworks</li>
</ul>

<h2 style="color:#2be382">Functionality</h2>
<hr>
<p>Working with an <span style="color:#2be382">element</span> starting from the start page, which allows the user to log into 
the application with an existing account or register. In both cases, the program checks the entered 
data.
</p>
<p>In case of successful authentication, the user gets access to the main page with chats and the search engine.
The search system allows find friends and create chats with them. All user chats and message history will be displayed.
</p>

<h2 style="color:#2be382">Installation and launch</h2>
<hr>

<ol>
    <li>Clone the repository: <a href="https://github.com/VladislavKirbut/Element.git">https://github.com/VladislavKirbut/Element.git</a></li>
    <li>Сonfigure and connect the database</li>
    <li>Build: mvn clean package</li>
    <li>Launch program: </li>
    <ul>
        <li>Without Docker:</li>
            <ul>
                <li>java -jar target/ElementProject.jar</li>
            </ul>
        <li>With Docker</li>
            <ul>
                <li>docker build -t element-spring-project:0.0.1 .</li>
                <li>docker run -d -p 8080:8080 element-spring-project:0.0.1</li>
            </ul>
    </ul>
</ol>


