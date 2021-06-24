# health
health 동작 예약 서비스

![image](https://user-images.githubusercontent.com/45971330/123205290-92817b00-d4f4-11eb-8422-a1bd6c74565e.png)

1. CheckPoint1. Saga

이벤트 Pub / Sub 구현

publish


![image](https://user-images.githubusercontent.com/45971330/123205972-c5783e80-d4f5-11eb-814b-0730b97a9a7c.png)

sub


![image](https://user-images.githubusercontent.com/45971330/123206048-ed67a200-d4f5-11eb-8f42-65d95d1b030c.png)

2. CheckPoint2. CQRS

my page에서 stauts가 변경될 때마다, event를 수신하여 command와 view를 분리시킴 

![image](https://user-images.githubusercontent.com/45971330/123215521-84872680-d503-11eb-8204-d1d904d470d1.png)


3. CheckPoint3. Correlation

![image](https://user-images.githubusercontent.com/45971330/123214139-cd3de000-d501-11eb-9648-867699c35a92.png)

![image](https://user-images.githubusercontent.com/45971330/123214943-cc597e00-d502-11eb-8086-ad5052ac9901.png)

![image](https://user-images.githubusercontent.com/45971330/123215043-e6935c00-d502-11eb-9640-7906b307bead.png)


5. CheckPoint4. Req/Resp


![image](https://user-images.githubusercontent.com/45971330/123213509-09bd0c00-d501-11eb-8948-0c37ce99aa5f.png)


5. CheckPoint5. Gateway


![image](https://user-images.githubusercontent.com/45971330/123208903-d2e3f780-d4fa-11eb-9798-15b9409289aa.png)


![image](https://user-images.githubusercontent.com/45971330/123208979-ef802f80-d4fa-11eb-9406-2f4edf34d79c.png)


![image](https://user-images.githubusercontent.com/45971330/123209207-3ff78d00-d4fb-11eb-85a7-1bc380b88e95.png)


6. CheckPoint6. Polyglot

call의 porm.xml h2 > hsql로변경

![image](https://user-images.githubusercontent.com/45971330/123209777-168b3100-d4fc-11eb-98bf-6b1960d2ee92.png)



7. CheckPoint7. Deploy/ Pipeline

```
git clone https://github.com/kjh951203/health.git

mvn package -Dmaven.test.skip=true

az acr build --image junghwan.azurecr.io/help:v1 \
  --registry junghwan \
  --file Dockerfile . 
  
az acr build --image junghwan.azurecr.io/help:latest \
  --registry junghwan \
  --file Dockerfile . 
```

kubectl apply -f call/kubernetes/deployment.yml

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: call
  namespace: default
  labels:
    app: call
spec:
  replicas: 1
  selector:
    matchLabels:
      app: call
  template:
    metadata:
      labels:
        app: call
    spec:
      containers:
        - name: call
          image: junghwan.azurecr.io/call:latest
          ports:
            - containerPort: 8080
          env:
            - name: configurl
              valueFrom:
                configMapKeyRef:
                  name: apiurl
                  key: url
          readinessProbe:
            httpGet:
              path: '/actuator/health'
              port: 8080
            initialDelaySeconds: 10
            timeoutSeconds: 2
            periodSeconds: 5
            failureThreshold: 10
          livenessProbe:
            httpGet:
              path: '/actuator/health'
              port: 8080
            initialDelaySeconds: 120
            timeoutSeconds: 2
            periodSeconds: 5
            failureThreshold: 5
          resources:
            limits:
              cpu: 500m
            requests:
              cpu: 200m
```

![image](https://user-images.githubusercontent.com/45971330/123217227-4e4aa680-d505-11eb-996d-ef2c3792c511.png)


8. CheckPoint8. Circuit Breaker

```
siege -c100 -t60S -r10 -v --content-type "application/json" 'http://gateway:8080/calls POST {"price":"15000", "name":"kim", "part":"benchpress"}'
```
![image](https://user-images.githubusercontent.com/45971330/123224212-928d7500-d50c-11eb-886e-3ab2f1bb9606.png)

![image](https://user-images.githubusercontent.com/45971330/123232251-ecde0400-d513-11eb-8a05-30e973e5c339.png)

9. CheckPoint9. Autoscale (HPA)

```
kubectl autoscale deployment call --cpu-percent=15 --min=1 --max=3
kubectl autoscale deployment payment --cpu-percent=15 --min=1 --max=3
```

![image](https://user-images.githubusercontent.com/45971330/123225388-a4bbe300-d50d-11eb-99e5-97da15b52f5e.png)

```
siege -c100 -t60S -r10 -v --content-type "application/json" 'http://gateway:8080/calls POST {"price":"15000", "name":"kim", "part":"benchpress"}'
```

![image](https://user-images.githubusercontent.com/45971330/123229730-88ba4080-d511-11eb-95b2-52605a31776d.png)


10. CheckPoint10. Zero-downtime deploy (Readiness Probe)

![image](https://user-images.githubusercontent.com/45971330/123233161-ca98b600-d514-11eb-913f-f1f19c7a4645.png)


![image](https://user-images.githubusercontent.com/45971330/123233101-be145d80-d514-11eb-9a74-4658f2fc7477.png)

![image](https://user-images.githubusercontent.com/45971330/123234467-eea8c700-d515-11eb-8228-8e4a630408f6.png)


```
$ kubectl create configmap apiurl --from-literal=url=http://payment:8080 --from-literal=fluentd-server-ip=10.xxx.xxx.xxx -n default
```
![image](https://user-images.githubusercontent.com/45971330/123231252-02066300-d513-11eb-8440-a91b487044fe.

11. CheckPoint11. Config Map/ Persistence Volume

![image](https://user-images.githubusercontent.com/45971330/123230720-7d1b4980-d512-11eb-8380-9331a6a173ce.png)


![image](https://user-images.githubusercontent.com/45971330/123230993-bfdd2180-d512-11eb-8dfd-9b7cd70eb9f8.png)

png)


