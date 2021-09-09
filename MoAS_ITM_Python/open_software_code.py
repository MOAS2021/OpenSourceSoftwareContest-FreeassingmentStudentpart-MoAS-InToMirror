import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

import tkinter as tk
import tkinter.font
from tkinter import Label,Tk
import time
import requests
from bs4 import BeautifulSoup

font = '15' #기본 폰트사이즈

divide=[[0,30], #1~6사분면까지를 이중배열을 통해서 포지셔닝한 좌표배열
        [380,30],
        [0,450],
        [380,450],
        [0,800],
        [380,800]
        ]

def datePos(num):#시간위젯의 포지션을 정해주는 함수

    global today,l,Date,sig
    l = tk.Label(app,
                  text= '',
                  font=('맑은고딕','50'),
                  bg='black',
                  fg='white',
                 )

    l.place(x=divide[num][0],y=divide[num][1])

    today = tk.Label(app,
                     text='',
                     bg='black',
                     fg='white',
                     font=('맑은고딕',font)
                     )

    today.place(x=divide[num][0],y=divide[num][1]+100)
    
def date():#시간을 불러오는 함수
    global today,l,Date,sig
    Date = time.strftime('%m월 %d일', time.localtime(time.time())).encode('unicode-escape').decode('unicode-escape')
    day = int(time.strftime('%w', time.localtime(time.time())))
    day_word=['일','월','화','수','목','금','토']
    Date = Date + ' ' + day_word[day] + '요일'

    sig = time.strftime("%H:%M")
    l.config(text=sig)
    today.config(text = Date)

    #1s
    app.after(1000,date)# 1초마다 한번식 시간을 불러온다

def weatherPos(num):#날짜위젯의 포지션을 정해주는 함수

    global en0,en1,en2,en3,en4,cast_intro,day_low,day_high,cur_temp,location
    print('weatherPos')
    en0 = tk.Label(app,
                   text=name[-1],
                   fg='white',
                   bg='black',
                   font=('맑은고딕',font)
                   )

    en1 = tk.Label(app,
                   text='',
                   fg='white',
                   font=('맑은고딕',font),
                   bg='black'
                   )

    en2 = tk.Label(app,
                   text='',
                   fg='white',
                   font=('맑은고딕',font),
                   bg='black'
                   )

    en3 = tk.Label(app,
                   text='',
                   fg='white',
                   font=('맑은고딕',font),
                   bg='black'
                   )

    en4 = tk.Label(app,
                   text='',
                   fg='white',
                   bg='black',
                   font=('맑은고딕',font)
                   )   

    en0.place(x=divide[num][0],y=divide[num][1])
    en1.place(x=divide[num][0],y=divide[num][1]+30)
    en2.place(x=divide[num][0],y=divide[num][1]+60)
    en3.place(x=divide[num][0]+40,y=divide[num][1]+90)
    en4.place(x=divide[num][0],y=divide[num][1]+90)

def weather():#날짜의 정보를 불러오는 함수

    global en0,en1,en2,en3,en4,cast_intro,day_low,day_high,cur_temp

    w_url = 'https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=ufg8&query={}+날씨'.format(name[-1])
    w_sauce = requests.get(w_url)
    w_html = w_sauce.text
    soupW = BeautifulSoup(w_html,'html.parser')
    temp = soupW.find('span',{'class':'todaytemp'})
    cur_temp = temp.text
    en1.config(text=cur_temp+'℃')


    intro = soupW.find('p',{'class':'cast_txt'})#오늘 날씨 한줄 소개

    cast_intro = intro.text

    en2.config(text=cast_intro)

    

    low = soupW.find('span',{'class':'min'})#최저온도

    day_low = low.text

    en3.config(text='/'+day_low)

    

    high = soupW.find('span',{'class':'max'})#최고온도

    day_high = high.text

    en4.config(text=day_high)

    #5m

    app.after(300000,weather)#5분에 한번씩 호출한다

def newPos(num):# 뉴스위젯의 포지션을 지정해주는 함수
    global Line1,news
    Line1 = tk.Label(app,
                    text='',
                    bg='black',
                    fg='white',
                    font=('맑은고딕','10')
                    )

    Line1.place(x=divide[num][0],y=divide[num][1])
def new():#뉴스를 불러오는 함수

    global Line1,news

    
 
    n_url = 'https://news.sbs.co.kr/news/newsHotIssue.do?plink=GNB&cooper=SBSNEWS'

    n_sauce = requests.get(n_url)

    n_html = n_sauce.text

    soupN = BeautifulSoup(n_html,'html.parser')

    head = soupN.find_all("strong",{'class':'mnprcl_tit'})

    news = [i.text for i in head] 
    news = '\n'.join(news[:8])

    Line1.config(text=news)

    #20m
    app.after(1200000,new)#20분에 한번씩 뉴스를 불러온다

def coronaPos(num):#코로나위젯의 포지션을 지정해주는 함수
    global COVID,COVID_CAST,covid

    COVID = tk.Label(app,
                    text='',
                    bg='black',
                    fg='white',
                    font=('맑은고딕','50')
                    )

    COVID_CAST = tk.Label(app,
                        text='하루확진자',
                        bg='black',
                        fg='white',
                        font=('맑은고딕',font)
                        )
    
    COVID_CAST.place(x=divide[num][0],y=divide[num][1])
    COVID.place(x=divide[num][0],y=divide[num][1]+30)
    
    
def corona():#코로나확진자수에 정보를 불러오는 함수
    global COVID,COVID_CAST,covid
    c_url = 'https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%BD%94%EB%A1%9C%EB%82%98+%ED%99%95%EC%A7%84%EC%9E%90'
    c_sauce = requests.get(c_url)
    c_html = c_sauce.text
    soupC = BeautifulSoup(c_html,'html.parser')
    covid = soupC.find('em',{'class':'info_variation'}).text

    COVID.config(text=covid)
    #30m
    app.after(18000000,corona)#30분에 한번씩 불러온다

def stockPos(num):#주식위젯의 포지션을 정해주는 함수
    
    global Mind,Mind_cast,cur,code,cur_name

    Mind = tk.Label(app,

                    text='',

                    bg='black',

                    fg='white',

                    font=('맑은고딕','30')

                    )

    Mind_cast = tk.Label(app,

                         text=name[-3],

                         bg='black',

                         fg='white',

                         font=('맑은고딕',font)

                         )

 
    Mind_cast.place(x=divide[num][0],y=divide[num][1])
    Mind.place(x=divide[num][0],y=divide[num][1]+30)
    
    
def stock():#주식의 정보를 불러오는 함수

    global Mind,Mind_cast,cur,cur_name
    
    s_url='https://finance.naver.com/item/main.nhn?code={}'.format(name[-3])

    s_sauce = requests.get(s_url)

    s_html = s_sauce.text

    soupS = BeautifulSoup(s_html,'html.parser')

    cur = soupS.find('div',{'class':'today'}).text
    
    cur = cur.split('\n')[3]

    Mind.config(text=cur)
    #7s

    app.after(7000,stock)#7초에 한번씩 정보를 불러온다

def ratePos(num):#환율위젯의 포지션을 지정해주는 함수
    global exchange,Rate,country

    exchange = tk.Label(app,

                text='',

                bg='black',

                fg='white',

                font=('맑은고딕','14')

                )
    cnt_name = tk.Label(app,
                        text=name[-2],
                        bg='black',
                        fg='white',
                        font=('맑은고딕','30')
                        )
 

    
    cnt_name.place(x=divide[num][0],y=divide[num][1])
    exchange.place(x=divide[num][0],y=divide[num][1]+70)
                   
def rate():#환율의 정보를 불러오는 함수
    global exchange,Rate

    r_url ='https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query={}+환율'.format(name[-2])

    r_sauce = requests.get(r_url)

    r_html = r_sauce.text

    soupR = BeautifulSoup(r_html,'html.parser')
    print(name)
    try:
        Rate = soupR.find('span',{'class':'spt_con dw'}).text.replace(' ','\n')
        
    except AttributeError:
        Rate = soupR.find('span',{'class':'spt_con up'}).text.replace(' ','\n')
        
    exchange.config(text=Rate)
    #15s
    app.after(15000,rate)#15초에 한번씩 불러온다

def sentancePos(num):#명언위젯의 포지션을 정해주는 함수
    global words,A

    words = tk.Label(app,

                    text='',

                    bg='black',

                    fg='white',

                    font=('맑은고딕','13')

                    )

 

    words.place(x=divide[num][0],y=divide[num][1])
    
def sentance():#오늘의 명언에대한 정보를 불러온다
    global words,A
    wise_url = 'https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&mra=blMy&qvt=0&query=%ED%9D%AC%EB%A7%9D%20%EB%AA%85%EC%96%B8'

    wise_sauce = requests.get(wise_url)

    wise_html = wise_sauce.text

    soupWise = BeautifulSoup(wise_html,'html.parser')

    A = list(soupWise.find('p',{'class':'lngkr'}).text)
    
    A.insert(20,'\n')
    A.insert(40,'\n')   
    A.insert(60,'\n')
    A = ''.join(A)
    words.config(text='"'+A+'"')

    #12h

    app.after(43200000,sentance)#12시간에 한번씩 불러온다

def luckPos(num):#운세위젯의 포지션을 지정해주는 함수

    global lucky,Luck
    lucky = tk.Label(app,
                    text = '',
                    bg='black',
                    fg='white',
                    font=('맑은고딕','13')
    )
    lucky.place(x=divide[num][0],y=divide[num][1])
    
def luck():#운세에대한 정보를 불러오는 함수
    
    global lucky,Luck
    luck_url = 'https://search.naver.com/search.naver?where=nexearch&sm=tab_etc&qvt=0&query=%EB%A7%90%EB%9D%A0%20%EC%9A%B4%EC%84%B8'
    luck_sauce = requests.get(luck_url)
    luck_html = luck_sauce.text
    soupL = BeautifulSoup(luck_html,'html.parser')
    Luck = list(soupL.find('p',{'class','text _cs_fortune_text'}).text)
    Luck.insert(20,'\n')
    Luck.insert(40,'\n')   
    Luck.insert(60,'\n')
    Luck.insert(80,'\n')
    Luck.insert(100,'\n')
    Luck.insert(120,'\n')
    Luck  = ''.join(Luck)
    lucky.config(text=Luck)
    #4h
    app.after(14400000,luck)#4시간에 한번씩 불러온다

def emptyPos(num):#빈칸위젯의 포지션을 정해주는 함수
    mt = tk.Label(app,
                  text= ' ',
                  bg='black'
                ).place(x=divide[num][0],y=divide[num][1])
    
# 많은 인원 편하게 인식 가능 
def empty():#함수의 용이함을 위해 기능은 없지만 넣어준 빈칸위젯에대한 함수
    pass

import cv2
import numpy as np
import os 

def CAMERA_ON():
    # 카메라 화면 불러오기  

    global cap,minH,minW,recognizer

    cap = cv2.VideoCapture(0) # 카메라 포트 번호 
    print("camera on")
    minW = 0.1*cap.get(3)#얼굴 너비 최소좌표

    minH = 0.1*cap.get(4)#얼굴 높이 최소좌표

    recognizer = cv2.face.LBPHFaceRecognizer_create()

    recognizer.read('/home/pi/Desktop/틴더 예제/trainer.yml') # 학습 파일 
    #C:/Users/Ryu_chan_joo/Desktop/공부라죠/공개소프트웨어 대회/틴더 예제/trainer.yml
    cascadePath = "/home/pi/Desktop/틴더 예제/haarcascade_frontalface_default.xml" # 얼굴인식 소스
    #C:/Users/Ryu_chan_joo/Desktop/공부라죠/공개소프트웨어 대회/틴더 예제/haarcascade_frontalface_default.xml
    global faceCascade

    faceCascade = cv2.CascadeClassifier(cascadePath)

    #id 카운터 시작

    global id,names

    id = 0

    # 얼굴 인식은 되지만 학습되지 않은 사람일 경우 None으로 확인 MJ = id :1  

    names = ['NONE', 'MJ', 'CJ', 'JH', 'DH', 'DW']
    
def FACE():
    
    global cap,minH,minW,recognizer
    ret, img =cap.read()                                 # 카메라에서 사진 가져오기

    img = cv2.flip(img, 1)                               # 화면 조정(상하반전)

    gray = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)          # 사진 흑백으로 바꿈

    faces = faceCascade.detectMultiScale(                # 얼굴인식 소스로 얼굴 찾기 

        gray,                                            # 검출하고자 하는 원본이미지

        scaleFactor = 1.2,                               # 1보다 커야 함

        minNeighbors = 5,                                # 얼굴 사이 최소 간격(픽셀)

        minSize = (int(minW), int(minH)),                # 위에서 설정해준 최소화면 크기보다 작으면 무시 

       )

 

    for(x,y,w,h) in faces:                                      # 사진에서 얼굴을 찾았다면 x,y,w,h 값을 얻어 처리 

        cv2.rectangle(img, (x,y), (x+w,y+h), (0,255,0), 2)      # 얼굴에 맞게 네모 박스 

        id, lose = recognizer.predict(gray[y:y+h,x:x+w])  

        

        if (lose > 90):#90%이상으로 얼굴이 매치되면 얼굴인식 성공

            id = names[id]

            confidence = "  {0}%".format(round(100 - lose))

            #print (id, confidence)
            return id
        else:
            #print ("학습되지 않은 얼굴입니다")
            pass
        
PosList=[datePos,weatherPos,newPos,coronaPos,stockPos,ratePos,sentancePos,luckPos,emptyPos]
List = [date,weather,new,corona,stock,rate,sentance,luck,empty]
#파이썬은 강력하기 때문에 배열안에 함수를 넣고 사용할 수 있습니다. 이것을 "이터레이터"라고 합니다

CAMERA_ON()
#print("카메라가 켜졌습니다\n")

def play(fun,n): # fun = List안에 있는 함수 어떤걸 쓸건지, n= 몇 사분면에 배치할건지 사분면 범위는 1~6
    PosList[fun](n)
    List[fun]()
    
def clear():#화면에 새롭게 검은 화면을 뿌려주는 초기화함수
    a1 = tk.Label(app,
             text='                   ',
             bg='black',
             fg='white',
             font=('맑은고딕','500')
             )
    a2 = tk.Label(app,
             text='                   ',
             bg='black',
             fg='white',
             font=('맑은고딕','500')
             )
    a3 = tk.Label(app,
             text='                   ',
             bg='black',
             fg='white',
             font=('맑은고딕','500')
             )
    a4 = tk.Label(app,
             text='                   ',
             bg='black',
             fg='white',
             font=('맑은고딕','500')
             )
    a5 = tk.Label(app,
             text='                   ',
             bg='black',
             fg='white',
             font=('맑은고딕','500')
             )
    a6 = tk.Label(app,
             text='                   ',
             bg='black',
             fg='white',
             font=('맑은고딕','500')
             )
    
    a1.place(x=divide[0][0],y=divide[0][1])
    a2.place(x=divide[1][0],y=divide[1][1])
    a3.place(x=divide[2][0],y=divide[2][1])
    a4.place(x=divide[3][0],y=divide[3][1])
    a5.place(x=divide[4][0],y=divide[4][1])
    a6.place(x=divide[5][0],y=divide[5][1])
    
global faceID

faceID = 0 #얼굴이 몇 초간 인식되었는지 확인하기 위한 변수

cred = credentials.Certificate("/home/pi/Downloads/chanju-4c75a-firebase-adminsdk-vlh9k-400f187298.json")
firebase_admin.initialize_app(cred,{'databaseURL':'https://chanju-4c75a-default-rtdb.firebaseio.com/'})#앱과 연동된 파이어베이스에서 값을 얻어오기 위한 키와 url정보

class USER:# 사용자정보를 가공하는 클래스
    
    def __init__(self):
        self.dir = db.reference()#현재 파이어베이스에 업로드된 모든 정보를 불러온다
        self.whole= self.dir.get()#whole변수에 저장
        
    def process(self,user):#불러온 정보를 가공하는 함수

        who = [['ID1','ID1LT','ID1RT','ID1LM','ID1RM','ID1LB','ID1RB',['ID1CODE','ID1COUNTRY','ID1LOCAL']],
                ['ID2','ID2LT','ID2RT','ID2LM','ID2RM','ID2LB','ID2RB',['ID2CODE','ID2COUNTRY','ID2LOCAL']],
                ['ID3','ID3LT','ID3RT','ID3LM','ID3RM','ID3LB','ID3RB',['ID3CODE','ID3COUNTRY','ID3LOCAL']],
                ['ID4','ID4LT','ID4RT','ID4LM','ID4RM','ID4LB','ID4RB',['ID4CODE','ID4COUNTRY','ID4LOCAL']]]
                #파이어베이스에 올라와있는 모든 키값을 배열에 저장
        global name
        name = []# 현재 사용자가 갖고있는 ID를 도출하기위한 배열

        self.reverse = dict(map(reversed,self.whole.items()))
        number = self.reverse[user]
        #number엔 현재 사용자가 몇번째 ID인지에대한 정보가 담겨있음
        if number[2] == '1':#만약 사용자가 ID1이라면
            for i in range(1,7):#사용자인터페이스의 정보를 배열안에 정리함
                name.append(self.whole[who[0][i]])
                if i == 6:#6번째부턴 추가정보를 3차배열안에 정리함
                    for i in range(0,3):
                        try:#정보가 없을때 나오는 에러는 통과
                            name.append(self.whole[who[0][7][i]])
                        except KeyError:
                            pass
                        
        elif number[2] == '2':
            for i in range(1,7):
                name.append(self.whole[who[1][i]])
                if i == 6:
                    for i in range(0,3):
                        try:
                            name.append(self.whole[who[1][7][i]])
                        except KeyError:
                            pass
                        
        elif number[2] == '3':
            for i in range(1,7):
                name.append(self.whole[who[2][i]])
                if i == 6:
                    for i in range(0,3):
                        try:
                            name.append(self.whole[who[2][7][i]])
                        except KeyError:
                            pass
                        
        elif number[2] == '4':
            for i in range(1,7):
                name.append(self.whole[who[3][i]])
                if i == 6:
                    for i in range(0,3):
                        try:
                            name.append(self.whole[who[3][7][i]])
                        except KeyError:
                            pass
        else:
            pass
        fun = ['시간','오늘의 날씨','오늘의 뉴스','코로나 확진자수','주식','환율','오늘의 명언','오늘의 운세','빈칸']#파이어베이스에서 넘오는 밸류값
        val = []
        #파이어 베이스에서 받아온 밸류값을 파이썬 내에서 활용하기 위해 숫자로 치환
        for i in name:
            for j in fun:
                if i == j:
                    val.append(fun.index(j))
        val.append(name[-1])
        val.append(name[-2])
        val.append(name[-3])
        return val
        #val은 사용자의 위젯과 위젯포지션의 정보가 정리되어있음
app = tk.Tk()#gui실행
app.configure(bg='black')#배경은 검은색
app.attributes('-fullscreen',True)#전체화면 설정



while True:
    time.sleep(0.5)
    
    if FACE() == None: # 얼굴 인식이 안될때

        faceID = faceID + 1
        
        if faceID >= 20: # 얼굴인식이 10초간 안되면
        
            try:
                clear()#화면을 지운다
            except NameError:
                pass
            
    else: #얼굴 인식이 될때
        user = FACE()
        #user에 현재 얼굴인식된 사람의 정보를 담음
        Now = USER()

        if user != None:#None 오류를 막기위한 조건문
            faceID = 0 #얼굴인식시간 초기화

            on = Now.process(user)
            #on은 val의 값이 들어있다
            
            for data in on:
                
                play(data,on.index(data))
                
                if on.index(data) == 5:
                    break
                else:
                    pass
    app.update()#불러온 정보 업데이트

#'오늘의 뉴스','코로나 확진자수','환율','주식','오늘의 명언','오늘의 운세'
