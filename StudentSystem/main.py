from manager import StudentManager


def main():

    manager = StudentManager()

    while True:

        print("\n========== 学生信息管理系统 ==========")
        print("1. 添加学生")
        print("2. 查看所有学生")
        print("3. 查询学生")
        print("4. 修改学生")
        print("5. 删除学生")
        print("6. 成绩统计")
        print("0. 退出系统")

        choice = input("请输入你的选择：")

        if choice == "1":
            manager.add_student()

        elif choice == "2":
            manager.show_students()

        elif choice == "3":
            manager.find_student()

        elif choice == "4":
            manager.update_student()

        elif choice == "5":
            manager.delete_student()

        elif choice == "6":
            manager.score_statistics()

        elif choice == "0":
            print("欢迎下次使用！")
            break

        else:
            print("输入错误，请重新选择！")


if __name__ == "__main__":
    main()