# import tools
# from tools import getMAX

# a = int(input("请输入第一个数："))
# b = int(input("请输入第二个数："))
# print("得到最大的数：", tools.getMAX(a, b))

# name = input("请输入你的名字：")
# score = int(input("请输入你的成绩："))

# with open("score.txt", "a", encoding="utf-8") as f:
#     f.write(name + " " + str(score) + "\n")

# with open("score.txt", "r") as f:
#     print(f.read())

# try:
#     a = int(input("请输入第一个数："))
#     b = int(input("请输入第二个数："))
#     result = a / b
#     print("得到的商：", result)
# except ZeroDivisionError:
#     print("除数不能为零")
# except ValueError:
#     print("输入的不是数字")
# finally:
#     print("程序结束")


# class Employee:
#     def __init__(self, name, salary):
#         self.name = name
#         self.__salary = salary

#     def get_salary(self):
#         return self.__salary

#     def work(self):
#         print(self.name + "正在工作")


# class Programmer(Employee):
#     def work(self):
#         print(self.name + "正在写代码")


# class Manager(Employee):
#     def work(self):
#         print(self.name + "正在管理")


# def start_work(employee):
#     employee.work()

# p1 = Programmer("小明", 10000)
# m1 = Manager("小红", 20000)


# start_work(p1)
# start_work(m1)
# print("小明的工资是：", p1.get_salary())
# print("小红的工资是：", m1.get_salary())


class Student:
    school = "湖南科技大学"
    count = 0

    def __init__(self, name, age):
        self.name = name
        self.age = age
        Student.count += 1

    def introduce(self):
        print(
            "我叫"
            + self.name
            + "，今年"
            + str(self.age)
            + "岁，我在"
            + Student.school
            + "上学"
        )

    @classmethod
    def show_count(cls):
        print("学生总数为：" + str(cls.count))

    @staticmethod
    def check_age(age):
        if age < 0:
            print("年龄不能为负数")
        else:
            print("年龄合法")


S1 = Student("小明", 18)
S2 = Student("小红", 19)
S1.introduce()
S2.introduce()
Student.show_count()
Student.check_age(-1)
