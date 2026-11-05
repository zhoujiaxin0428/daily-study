class Student:
    def __init__(self, student_id, name, age, score):
        self.student_id = student_id
        self.name = name
        self.age = age
        self.score = score

    def show_info(self):
        print("学号：", self.student_id)
        print("姓名：", self.name)
        print("年龄：", self.age)
        print("成绩：", self.score)
        print("--------------------")
