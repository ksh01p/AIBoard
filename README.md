# AIBoard: Real-Time Audio-to-Subtitle Translation Service

AIBoard는 음성을 텍스트로 변환한 뒤, 이를 원하는 언어로 실시간 번역하여 자막으로 제공하는 서비스를 구현한 프로젝트입니다. Whisper API와 ChatGPT API를 활용하여 정밀한 텍스트 변환 및 언어 번역 기능을 제공합니다.

![1](https://github.com/user-attachments/assets/fe2aadbd-f54c-4aa4-abad-3d3787c679ec)


## 🚀 주요 기능 (Features)

1. **음성 텍스트 변환 (Speech-to-Text)**  
   OpenAI Whisper API를 사용하여 음성을 고정밀 텍스트로 변환.

2. **실시간 언어 번역 및 생성 (Real-Time Translation & Generation)**  
   ChatGPT API를 활용하여 변환된 텍스트를 사용자가 원하는 언어로 번역 및 생성.

3. **자막 출력 (Subtitle Rendering)**  
   번역된 텍스트를 화면에 자막 형태로 표시.

4. **사용자 친화적 인터페이스 (User-Friendly UI)**  
   간단하고 직관적인 UI를 통해 모든 사용자에게 접근 가능.

---

## 📚 기술 스택 (Tech Stack)

- **Frontend:**  
  - HTML, CSS, JavaScript
  - 자막 출력 UI 설계 및 사용자 입력 처리

- **Backend:**  
  - Python
  - OpenAI Whisper API 및 ChatGPT API와의 연동

- **API 사용:**  
  - [OpenAI Whisper API](https://openai.com/whisper)
  - [OpenAI ChatGPT API](https://openai.com/chatgpt)

---

## 🛠️ 설치 및 실행 방법 (Installation & Setup)

1. **필수 요구 사항**:
   - Python 3.8 이상
   - API 키:
     - OpenAI API Key (Whisper 및 ChatGPT)

2. **프로젝트 클론**:
   ```bash
   git clone https://github.com/ksh01p/AIBoard.git
   cd AIBoard
3. **필요 패키지 설치**
   pip install -r requirements.txt
4. **환경 변수 설정: .env 파일을 프로젝트 루트에 생성하고 아래 내용을 추가하세요: **
   OPENAI_API_KEY=your_openai_api_key
   
## 🌟 사용법 (How to Use)
1. **애플리케이션을 실행합니다.**
2. **언어 선택 메뉴에서 번역하고자 하는 언어를 선택합니다.**
3. **음성을 입력하면 Whisper API를 통해 텍스트로 변환됩니다.**
4. **변환된 텍스트가 ChatGPT API를 통해 원하는 언어로 번역됩니다.**
5. **맞춤형 자막 스타일을 설정하여 자막 크기와 색상을 조정할 수 있습니다.**
6. **번역된 텍스트가 자막 형태로 화면에 표시됩니다.**


<img width="1440" alt="1" src="https://github.com/user-attachments/assets/afdc3e6e-8a70-4148-b8ae-07bdfc3a4b02">

[Watch the video](https://youtu.be/3OSxXfjrh8c)

---
## FE + BE 개발 - 김민경(https://github.com/kmk01p)
## 기획 - 김수형(https://github.com/ksh01p)

   
