const list1 = ["Cars", "Motorbikes", "Drones", "Boats"];

const list2 = ["Horses", "Cows", "Dolphins", "Goats"];

document.addEventListener("DOMContentLoaded", () => {
  const leftList = document.querySelector("ul.left");
  const rightList = document.querySelector("ul.right");

  addListElements(list1, leftList, (e) =>
    doubleClickHandler(e.target, leftList, rightList)
  );
  addListElements(list2, rightList, (e) =>
    doubleClickHandler(e.target, leftList, rightList)
  );
});

const doubleClickHandler = (target, leftList, rightList) => {
  if (target.parentNode == leftList) {
    leftList.removeChild(target);
    rightList.appendChild(target);
  } else {
    rightList.removeChild(target);
    leftList.appendChild(target);
  }
};

const addListElements = (textList, elementList, handler) => {
  textList.forEach((x) => {
    const li = document.createElement("li");
    li.innerText = x;
    li.addEventListener("dblclick", handler);
    elementList.appendChild(li);
  });
};
