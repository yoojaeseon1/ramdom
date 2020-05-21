## 2020.05.22

### @Responsebody

- @Responsebody 어노테이션이 붙은 메소드의 리턴 값은 HTTP Response Body에 직접 쓰여지게 된다.

- 리턴되는 데이터의 타입에 따른 MessageConverter에 의해 변환이 이뤄진 이후 쓰여진다.

객체 : MappingJacksonHttpMessageConverter

String : StringHttpMessageConverter

- 객체타입의 경우 MappingJacksonHttpMessageConverter에 의해 필드 값이 JSON형태로 변환된다.

- MappingJacksonHttpMessageConverter : {클래스의 필드 : 해당 필드의 getter()메소드의 값} 을 맵핑시켜준다.

전달되는 Content-Type은 application/json;charset=UTF-8 이다.

- 객체를 리턴하는 메소드에서 @ResponseBody를 제거하고 실행했더니 맵핑된 url의 path를 view의 jsp파일에서 찾지 못했다는 404에러가 발생한다. 

- StringHttpMessageConverter : 리턴되는 String을 그대로 Response Body에 싣는다.

전달되는 Content-Type은 text/plain;charset=ISO-8859-1 이다.


과정

Controller의 메소드의 return되는 값 -> message converter에서 response body에 쓰여지는 데이터로 변환 -> 브라우저로 전송

---

### 주입방식(생성자/setter/필드)


- final로 선언이 가능하다.(객체가 변하지 않는다.)

필드를 final로 선언만 하는 경우에는 에러가 발생한다.

하지만 final로 선언 이후 생성자에서 초기화를 하면 에러가 발생하지 않는다.


- 더 공부할 자료는 찾았는데.... 좀 길어서 지금 정신으론 집중해서 공부 못할거 같아요..

내일 일어나서 할게요...

---

### 접근제어자(private/default/protected/public), 필드값을 private로 하는 이유


#### private : private이 붙은 메소드/변수는 해당 클래스에서만 접근 가능

#### default : 따로 설정하지 않을 경우의 접근자. 메소드/변수를 해당 패키지에서만 접근 가능

같은 패키지 안에 있는 다른 클래스에서 인스턴스를 생성하면 default인 메소드/변수에 접근 가능한 것이다.

ex)

public class A{

   Stirng name = "yoo";
}


// 같은 패키지에 있는 클래스
	
	public class B{
	
		public static void main(String[] args) {
		
			A a = new A();
			System.out.println(a.yoo); // 같은 패키지 이므로 정상적으로 출력된다.
			
		}
	  
	}

#### protected : 같은 패키지, 다른 패키지에 있는 해당클래스를 상속받은 클래스에서 접근 가능

#### public : 모든 클래스에서 접근 가능


#### 접근제한자 사용

#####  클래스       : public, default

#####  생성자       : public, protected, default, private

#####  멤버변수    : public, protected, default, private

#####  멤버메소드 : public, protected, default, private


private -> default -> protected -> public 순으로 보다 많은 접근을 허용한다.


#### 필드 값에 private을 사용하는 이유

1. 프로그램이 실행중일 때 임의로 수정되는 것을 방지하기 위해(실행 전 다른 프로그래머들에 의해 수정되는 것이 아니라)

- 사용하는 사람(고객)의 입장에서 원하는 값을 얻기 위해 내부적으로 변수/메소드들이 어떻게 조작되는지 알 필요가 없기 때문에


1. DTO 인스턴스 경우 DB의 데이터가 setter를 통해 초기화되었는데 이를 임의로 조작하면 안되기 때문에 사용

2. 특정 필드 값이 가지면 안되는 값(ex)음수가 되면 안됌)의 경우 직접 접근하지 못하고 setter에서 해당 값이 될 수 없도록 막을 수 있다.

--- 


####  DAO / DTO 차이

DAO(Data Access Object)

- DB와의 연결을 담당하는 클래스

- SqlSession을 통해 DB에 접근한다.


DTO(Data Transfer Object)

- 하나의 레코드를 하나의 클래스에 매칭시켜 정의한 클래스

- DAO의 특정 메소드를 통해 받아오는 레코드와 매칭되어있다.

---

####  service인터페이스 만드는 건 옛날 방식(형 소스(카카오톡에 보내준 URL) 참고해서 소스 수정해보자)

---

####  구현체가 여러개 일때 어떤 구현체가 주입받는지???

- org.springframework.beans.factory.NoSuchBeanDefinitionException: No unique bean of type [com.yoo.service.RandomService] is defined: expected single matching bean but found 3: [randomServiceImpl1, randomServiceImpl2, randomServiceImpl3]

에러 발생

원인 : 주입받을 구현체가 하나만 있어야 그 구현체를 특정해서 주입할 수 있는데 어떤 구현체를 주입할 지 컨테이너가 판단을 할 수 없어서 에러 발생

해결 방법 : setter/필드 주입 방법으로 @Qaulifier(name), @Resouce(name="name")을 사용해야 한다.(생성자에는 @Qualifier, @Resource를 사용 할 수 없기 때문에 불가능하다.)


	@Service("random1")
	public class RandomServiceImpl2 implements RandomService {}
	
	@Service("random2")
	public class RandomServiceImpl2 implements RandomService {}
	
	
	@Service("random3")
	public class RandomServiceImpl2 implements RandomService {}


HomeController

	@Autowired
	@Qualifier("random1")
	private RandomService randomService;

와 같은 방식으로 지정한 Service의 값을 Qualifier의 값에 넣어주면 된다.

---

####  @Component, @Service, @Controller 차이

@Component : Spring에서 관리하는 component를 나타내는 가장 기본적인 타입

@Controller : @RequestMapping 어노테이션을 해당 어노테이션 밑에서만 사용할 수 있다.(@Resource로 하면 입력한 url에 맵핑되는 메서드가 있어도 찾지를 못한다.)

@Service : 아직까지는 @Component에서 추가된 기능이 없다.(@Component를 대신 사용해도 동일하게 작동한다.)

---

#### 오류 해결

HTTP Status 406 에러 발생 했던 것

##### 원인 : dto를 JSON타입으로 변환 시켜주는 depencency를 pom.xml에 추가하지 않아서


##### 해결 방법

 		<dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-asl</artifactId>
			<version>1.9.13</version>
		</dependency>

를 추가

---