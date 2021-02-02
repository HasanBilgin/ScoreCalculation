## Proje Gereksinimleri

<div style="margin:50px">
    <img align="center" src="https://www.docker.com/sites/default/files/d8/2019-07/vertical-logo-monochromatic.png" width=100px  />
    <img align="center" src="https://logos-download.com/wp-content/uploads/2016/10/Java_logo_icon.png" width=100px />
    <img align="center" src="https://seeklogo.com/images/S/spring-logo-9A2BC78AAF-seeklogo.com.png" width=100px />
    <img align="center" src="https://maven.apache.org/images/maven-logo-black-on-white.png" width=100px />
    <img align="center" src="https://zdnet3.cbsistatic.com/hub/i/r/2018/02/16/8abdb3e1-47bc-446e-9871-c4e11a46f680/resize/370xauto/8a68280fd20eebfa7789cdaa6fb5eff1/mongo-db-logo.png" width=100px />
    <img align="center" src="https://miro.medium.com/max/2800/1*y5YLuOKO5XM7MOzve6XsDQ.png" width=100px />
</div>

## Kurulum
<hr/>

### Kullanıcı Arayüzü Kurulum
Kullanıcı arayüzünün çalıştırılabilmesi için ekteki scriptlerin sırasıyla çalıştırılması yeterlidir.
```
cd ScoreCalculation/web
```
```
npm install
```
```
npm run serve
```
### Backend Kurulum
Backend servislerinin çalıştırılabilmesi için ekteki scriptlerin sırasıyla çalıştırılması yeterlidir.
```
cd ScoreCalculation
```
```
mvn clean install
```
```
mvn clan install (Maven kurulu değilse yukarıdaki script yerine bu script çalıştırılmalıdır)
```
```
mvn spring-boot:run
```
```
mvnw spring-boot:run (Maven kurulu değilse yukarıdaki script yerine bu script çalıştırılmalıdır)
```
### Veri tabanı kurulumu
```
cd ScoreCalculation
```
```
docker-compose up -d
```

Yukarıdaki adımlar tamamlandıktan sonra arayüze aşağoıdaki adresten erişilebilir.
* http://localhost:8080/

MongoDB veritabanı sunucu port bilgileri  aşağıdaki gibidir.
* localhost:27017

## Skor Hesaplama Kullanıcı Arayüzü
<hr/>
<img align="center" src="web/frontend.png" width="400px" height="500px"/>

## Skor Hesaplama Sonucu
<hr/>
<img align="center" src="web/frontend_score.png" width="400px" height="400px"/>

### Müşteri Skor Hesaplama Backend Servis Testi

* Method : POST
* URL: localhost:8081/insert-score
* Body : 
```
{
    "fullName": "Hasan Bilgin",
    "identityNo": "11111111111",
    "phoneNumber": "2222222222",
    "incomeTranche": "2000",
    "city": "34"
}
```
