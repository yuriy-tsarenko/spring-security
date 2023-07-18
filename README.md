## **SPRING SECURITY**

Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications.

Spring Security is a framework that focuses on providing both authentication and authorization to Java applications. Like all Spring projects, the real power of Spring Security is found in how easily it can be extended to meet custom requirements

## Features

* Comprehensive and extensible support for both Authentication and Authorization
* Protection against attacks like session fixation, clickjacking, cross site request forgery, etc
* Servlet API integration
* Optional integration with Spring Web MVC

# Continuous delivery principles

### Repeatable reliable process

Organizational processes have their own development lifecycle. They usually start as manual checklists or “playbooks”, which are lists of tasks performed manually. Later they may be automated with software tools and scripts. Committing these playbooks to software scripts ensures that they are repeatable. If the checklist needs to be run again, a team member can execute the script. Reliability is gained when these playbook scripts are run consistently between environments. For example, the playbook for deploying code to a development or staging environment should mirror the production environment as closely as possible. This reliable consistency between environments and executions eliminates a whole class of consistency bugs.

### Automate everything

Automation is a key value of CD. Human time is expensive and should be conservatively spent on creative exercises instead of tedious playbook task running. A manual process is not truly repeatable and reliable until it has been committed to code and is executable automatically on demand. Automated tasks can be composed together to create further levels of automation. Automate as much as possible: tests, releases, configuration changes, and more.

### Version control

A cornerstone of CD, version control is an absolute must for any serious software project. Version control enables a team of developers to efficiently collaborate on a shared codebase. Git is the most widely used version control system and a great companion for CD. Version control enables ‘undo’ functionality by allowing rollbacks to previous release candidates. In addition to code; configuration, scripts, databases, documentation should all be version controlled to track edits throughout history.

### Build in quality

In CD, quality is not an afterthought that is kicked to the QA team. Quality is baked into every step of the release pipeline. The central feedback loop of CD is a constant re-examination of the quality being delivered to end users. New features are delivered with sets of automated tests that ensure new code is bug-free and meeting quality expectations. Project planning for new feature releases should include considerations around analytics, performance monitoring, and automated testing instrumentation tasks.

### Do the hardest parts first

Painful, time-consuming or error-prone tasks compound over time. Painful tasks should be addressed as soon as possible to prevent a compounding loss of energy. Imagine a painful chore that takes 20 minutes to do and is run five times a week. That compounds to a 100 painful minutes a week and \~400 painful minutes a month, etc. Imagine you could address this chore and optimize it to prevent the painful time altogether. Obviously, that would be a win.

“Do the hardest parts first” is also an exercise to help identify weaknesses in the organizational process. If there is a task that is procrastinated or actively avoided it is an indicator that it could be an area of improvement and should be actively pursued. Teams should regularly touch hard parts to stay familiar and keep them at the forefront of planning conversations.

### Everyone is responsible

The entire organization should be focused and incentivized to ensure the end user deliverable is as high quality as possible. Product Managers should plan with attention to deployment and quality assurance. The Security team should be actively involved in the release process. QA team members should test development and staging environments with as much rigor as they would on production to catch any failures before eventual release. Developers should actively be planning for production release.

### Implemented tools:

* GitHub workflow - Java CI with Gradle
* Google cloud run - [How to setup Google cloud CD](https://cloud.google.com/run/docs/quickstarts/deploy-continuously#cloudrun_deploy_continuous_code-java)


## Testing with Mockito

Mockito is a mocking framework that tastes really good. It lets you write beautiful tests with a clean & simple API. Mockito doesn’t give you hangover because the tests are very readable and they produce clean verification errors. Read more about [features & motivations](https://github.com/mockito/mockito/wiki/Features-And-Motivations).

* Massive StackOverflow community voted Mockito the best mocking framework for java. Even though StackOverflow shuns questions that likely raise emotional debates the fact is [Mockito has the most votes](http://stackoverflow.com/questions/22697/whats-the-best-mock-framework-for-java).
* Top 10 Java library across all libraries, not only the testing tools. In late 2013 there was an analysis made of 30.000 GitHub projects. Although Mockito reached number 9 in the main report, mockito-core and mockito-all are the same tool and therefore the factual position of Mockito is number 4, surpassing famous tools like Guava or Spring. Treat this study as an indicator of a big impact that Mockito makes every day on unit tests written in Java.
* [Dan North](https://twitter.com/tastapod), the originator of [Behavior-Driven Development](http://en.wikipedia.org/wiki/Behavior-driven_development) wrote this back in 2008:
  “We decided during the main conference that we should use JUnit 4 and Mockito because we think they are the future of TDD and mocking in Java”

Se also: [Mockito](https://site.mockito.org), [Mockito-verify](https://www.baeldung.com/mockito-verify)
