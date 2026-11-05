"""Flask 入口（和 FastAPI 二选一，先都看看再决定用哪个）。

启动：
    python app_flask.py
然后浏览器打开 http://127.0.0.1:5000
"""

from flask import Flask, jsonify, request

app = Flask(__name__)


@app.get("/")
def index():
    return jsonify(message="Hello, Flask")


@app.get("/health")
def health():
    return jsonify(status="ok")


@app.post("/echo")
def echo():
    """POST 示例：把收到的 JSON 原样吐回去。"""
    return jsonify(received=request.get_json(silent=True))


if __name__ == "__main__":
    app.run(debug=True, port=5000)
