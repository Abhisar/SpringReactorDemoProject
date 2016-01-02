# SpringReactorDemoProject
 A sample project using Spring Reactor which implements a Async Task Execution Scenario
 
 Architecture:
 ===================================================================================
 Main Task Queue/Executor is WorkQueueAsyncTaskExecutor
 
 Two tasks have been created when initialized are in ACTIVE state.
 
 Then each task can go through multiple phases to change states which changes based on events.
 So events are registered on eventbus.
 
 Once task execution starts the processing begins in a async non-blocking manner
 =====================================================================================

===================================
|                                  |
| Steps to use/Setup the project : |
 ==================================
 Clone the Project and rename the folder as testReactor.
 
 To Compile the project :
  mvn compile
 
 To run the project go inside root folder after renaming:
  mvn spring-boot:run
 
