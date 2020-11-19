import os


ffrom locust import HttpLocust, TaskSet, task, HttpUser


class UserBehaviour(HttpUser):
    @task(1)
    def get_result(self):
        response = self.client.get("/calc-test-api/1.0/users/1/calculations")
        print("Response status code:", response.status_code)
        print("Response text:", response.text)


    @task(2)
    def computation(self):
        response = self.client.post("/calc-test-api/1.0/users/1/calculations",{"operand1":"1", "operator": "DIVIDE", "operand2": "0"})
        print("output is:", response.text)

class User(HttpUser):
    task_set=UserBehaviour
    min_wait = 500
    max_wait = 1000
    host="http://localhost:8080"