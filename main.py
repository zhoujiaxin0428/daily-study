"""FastAPI 入口。

启动（在项目根目录、已激活 .venv 的终端里）：
    uvicorn main:app --reload
然后浏览器打开 http://127.0.0.1:8000
自动生成的接口文档：http://127.0.0.1:8000/docs
"""

from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI(title="Python 学习项目", version="0.1.0")


@app.get("/")
def read_root():
    """最简单的接口，确认服务跑通。"""
    return {"message": "Hello, FastAPI"}


@app.get("/health")
def health():
    """健康检查，后面的接口联调常用。"""
    return {"status": "ok"}


class Item(BaseModel):
    """请求体模型，FastAPI 会自动做类型校验。"""

    name: str
    price: float
    count: int = 1


@app.post("/items")
def create_item(item: Item):
    """POST 示例：传 JSON 进去，算个总价返回。"""
    return {"received": item, "total": item.price * item.count}
