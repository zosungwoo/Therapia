# Therapia - 농잠 검색 및 작물 진단 서비스
*-2023 농림축산식품 공공빅데이터 활용 창업경진대회-*

![image](https://github.com/zosungwoo/Therapia/assets/30895117/c3682cec-0c48-4851-844a-dc8a2fa8a000)
최근 도시에서 농업을 체험하는 인구가 증가하고 반려식물에 대한 관심이 높아지고 있다. 특히 코로나19가 남긴 신체적/심리적 문제를 해결하고자 하거나 종식 이후 새로운 농업 활동 경험을 원하는 사람들이 관심이 많을 것이며, 우리는 이들을 위해 **Therapia** 서비스를 고안하였다.

Therapia는 라틴어로 '치료'라는 뜻을 담고 있다. 우리 서비스에서 치료는 두 가지의 의미를 담고 있다. 첫 번째는 '치유농장'을 통한 심적인 치료를 할 수 있다는 의미이고, 두 번째는 '작물 진단'을 통한 신체적인 치료를 할 수 있다는 의미이다. 

<br>

## Therapia 기능

1. '농장 소개'

![image](https://github.com/zosungwoo/Therapia/assets/30895117/85f54544-4b68-4179-8b64-f1098ff9a18c)

가고 싶은 지역을 검색하여 지정
해당 지역에서 ‘주말 농장’, ‘치유 농장’, ‘체험 농장’ 중 하나를 선택
각각 눌러 농장들의 위치를 지도를 통해 바로 확인할 수 있고 상세 설명은 지도 아래에서 확인 가능

<br>

2. '작물로 치유하기' 및 '모든 작물 및 레시피 조회'

![image](https://github.com/zosungwoo/Therapia/assets/30895117/cee84377-f29f-4ee0-8eec-34ae1df27a4a)

자신의 증상에 가까운 것들을 체크하면 그 증상에 맞는 효능 작물을 소개해주는 서비스를 제작

<br>

![image](https://github.com/zosungwoo/Therapia/assets/30895117/0f90e9f1-2d57-48a4-bcb5-c452830a4dd1)

모든 작물들을 조회할 수 있으며, 원하는 작물을 선택하면 제철 시기, 보관방법, 재료, 요리 및 레시피 정보를 제공

<br>

3. '농장 추천도'

![image](https://github.com/zosungwoo/Therapia/assets/30895117/c617d0a7-3ecc-4eae-ba67-f9a0808f54c7)

사람들이 농장을 알아볼 때 거리와 평점을 중요하게 생각하기 때문에 농장 선택 시 그 농장에 대한 정보와 함께 아래에 도넛차트로 추천도를 보여줌

도넛차트는 총 세 개이며 제목은 거리, 평점, 추천도임
거리는 현위치로부터 선택한 농장까지의 직선거리, 평점은 사람들이 리뷰와 함께 남긴 평점들의 평균, 추천도는 거리와 평균을 환산한 값의 평균임. 즉, 추천도를 측정하는 항목이 거리와 평점임

<br>

## 활용한 공공데이터
1) 농림축산식품 공공데이터 포털 - 텃밭 분양 상세정보 (오픈 API 활용해 XML/JSON 포맷으로 데이터 획득)
(https://data.mafra.go.kr/opendata/data/indexOpenDataDetail.do?data_id=20171122000000000916)
2) 공공데이터포털 - 서울시 주말농장 현황 (Excel 데이터를 활용해 데이터 획득 후 파이썬을 이용해 알맞게 가공)
(https://www.data.go.kr/data/15047308/fileData.do)
3) 공공데이터포털 – 농촌진흥청_치유농장 (오픈 API를 활용해 AJAX 방식으로 치유농장 데이터를 획득)
(https://www.data.go.kr/data/15081306/openapi.do)

<br>

## Therapia API 문서
[Swagger - REST API 문서화](https://github.com/zosungwoo/Therapia/files/12281674/API.pdf)

<br>

## Therapia ERD
![테라피아ERD](https://user-images.githubusercontent.com/63544044/172849734-612d9abb-6388-4c4b-a7ac-4e56ce3b3819.png)

|커밋|컨벤션|
|-----|-----|
|Feat : |기능 추가|
|Fix : |버그 해결|
|Docs : |문서 수정 (README.md)|
|Rename : |파일명, 폴더명 수정 또는 이동|
|Chore : |사소한 수정, 소스코드 외 내용|
|Test : |테스트 커밋|
