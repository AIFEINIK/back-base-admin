# 使用 Node.js 14 的官方镜像作为基础镜像
FROM node:14 AS builder

# 设置工作目录
WORKDIR /app

# 将项目文件复制到工作目录
COPY . .

# 安装依赖并构建项目
RUN npm install

# 构建项目
RUN npm run build:prod

# 使用 Nginx 镜像作为运行时镜像
FROM nginx:latest

# 设置工作目录
WORKDIR /usr/share/nginx/html

# 将构建好的前端应用复制到 Nginx 的默认静态文件目录
COPY --from=builder /app/dist .

# 配置 Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 暴露端口
EXPOSE 80

# 启动 Nginx
CMD ["nginx", "-g", "daemon off;"]
