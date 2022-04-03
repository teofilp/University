const list1 = ["Cars", "Motorbikes", "Drones", "Boats"];

const list2 = ["Horses", "Cows", "Dolphins", "Goats"];

document.addEventListener("DOMContentLoaded", () => {
  const leftList = document.querySelector("ul.left");
  const leftSelect = document.querySelector("select.left");
  const rightList = document.querySelector("ul.right");
  const rightSelect = document.querySelector("select.right");

  addListElements(list1, leftList, leftSelect, (e) =>
    doubleClickHandler(e.target, leftList, rightList, leftSelect, rightSelect)
  );
  addListElements(list2, rightList, rightSelect, (e) =>
    doubleClickHandler(e.target, leftList, rightList, leftSelect, rightSelect)
  );
});

const doubleClickHandler = (
  target,
  leftList,
  rightList,
  leftSelect,
  rightSelect
) => {
  if (target.parentNode == leftList) {
    leftList.removeChild(target);
    rightList.appendChild(target);

    for (child of leftSelect.children) {
      if (child.value == target.innerText) {
        leftSelect.removeChild(child);
      }
    }

    const option = document.createElement("option");
    option.value = option.innerText = target.innerText;
    rightSelect.appendChild(option);
  } else {
    rightList.removeChild(target);
    leftList.appendChild(target);

    for (child of rightSelect.children) {
      if (child.value == target.innerText) {
        rightSelect.removeChild(child);
      }
    }

    const option = document.createElement("option");
    option.value = option.innerText = target.innerText;
    leftSelect.appendChild(option);
  }
};

const addListElements = (textList, elementList, select, handler) => {
  textList.forEach((x) => {
    const li = document.createElement("li");
    const option = document.createElement("option");
    option.innerText = x;
    option.value = x;
    li.innerText = x;
    li.addEventListener("dblclick", handler);
    elementList.appendChild(li);
    select.appendChild(option);
  });
};
