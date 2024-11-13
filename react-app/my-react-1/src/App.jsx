
const MyHello = () => {
  return <h1>Hello React</h1>;
};

const MyWelcome = () => {
  const text = 'John';
  return <h1>Welcome {text} !</h1>;
};

const MyGreet = (props) => {
  const name = props.name;
  const age = props.age;
  return <h1>Hi {name} {age} !</h1>;
};

const getBmiStatus = (bmi) => {
  return bmi < 18 ? "過輕" : bmi > 23 ? "過重" : "正常";
};

const MyBMI = (props) => {
  const { username, userage, h, w } = props;
  const bmi = (w / (h*h)).toFixed(2);
  // 請加上 bmi 判斷 bmi < 18 過輕, bmi > 23 過重, 其餘正常
  return (<h1>{username} {userage} {bmi} {getBmiStatus(bmi)}</h1>)
};


function App() {
  return (
    <>
      <MyHello />
      <MyWelcome />
      <MyGreet name="Mary" age="18" />
      <MyBMI username="Mary" userage="18" h="1.6" w="45" />
    </>
  )
}

export default App
