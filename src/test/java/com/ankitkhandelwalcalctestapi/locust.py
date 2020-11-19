import os


from locust import HttpLocust, TaskSet, task, HttpUser


class UserBehaviour(HttpUser):
    @task(1)
    def launch_url(self):
        response = self.client.get("/calc-test-api/1.0/users/1/calculations/")
        print("Response status code:", response.status_code)
        print("Response text:", response.text)

class User(HttpUser):
    task_set=UserBehaviour
    min_wait = 500
    max_wait = 1000
    host="http://localhost:8080"