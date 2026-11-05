import json
import os

from student import Student


class StudentManager:
    def __init__(self):
        self.students = []
        self.load_data()

    # 读取数据

    def load_data(self):
        if os.path.exists("students.json"):
            with open("students.json", "r", encoding="utf-8") as file:
                data = json.load(file)

                for item in data:
                    student = Student(
                        item["student_id"], item["name"], item["age"], item["score"]
                    )

                    self.students.append(student)

    # 保存数据

    def save_data(self):
        data = []

        for student in self.students:
            item = {
                "student_id": student.student_id,
                "name": student.name,
                "age": student.age,
                "score": student.score,
            }
            data.append(item)

        with open("students.json", "w", encoding="utf-8") as file:
            json.dump(data, file, ensure_ascii=False, indent=4)

    # 添加学生
    def add_student(self):
        student_id = int(input("请输入学号："))
        name = input("输入姓名：")
        age = int(input("输入年龄："))
        score = float(input("输入成绩："))

        student = Student(student_id, name, age, score)

        self.students.append(student)
        self.save_data()

        print("添加成功！")

    # 查看所有学生
    def show_students(self):
        if not self.students:
            print("目前没有学生")
            return

        for student in self.students:
            student.show_info()

    # 查询学生
    def find_student(self):
        name = input("请输入要查询的姓名：")

        for student in self.students:
            if student.name == name:
                student.show_info()
                return

        print("没有查询到该学生")

    # 修改学生
    def update_student(self):
        student_id = int(input("输入需要修改的学号："))

        for student in self.students:
            if student.student_id == student_id:
                student.name = input("请输入姓名")
                student.age = int(input("请输入年龄"))
                student.score = float(input("请输入成绩"))

                self.save_data()

                print("修改成功！")
                return
        print("没有找到该学生")

    # 删除学生
    def delete_student(self):
        student_id = int(input("要删除的学号"))

        for student in self.students:
            if student.student_id == student_id:
                self.students.remove(student)
                self.save_data()

                print("删除成功")
                return
        print("没有找到该学生")

    # 成绩统计
    def score_statistics(self):
        if not self.students:
            print("目前没有学生")
            return
        scores = [student.score for student in self.students]

        print("最高成绩", max(scores))
        print("最低成绩", min(scores))
        print("平均成绩", sum(scores) / len(scores))
