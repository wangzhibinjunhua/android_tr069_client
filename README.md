# android_tr069_client
## 实现tr069 client 与acs 通信
- 1.open connection
- 2.cpe 发送inform request
- 3.acs 发送inform response
- 4.cpe 发送一个空的http post
- 5.acs 下发命令(GetParameterNames/GetParameterValues/Download/Upload/Reboot 等等)
- 6.cpe接收到acs下发的指令,解析xml数据.进行相应的reponse
