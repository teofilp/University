(() => {
  var count = 1;

  setInterval(() => {
    document.title = `(${count}) YouTube`;
    count++;
  }, 10000);
})();

(() => {
  var item = document.getElementById("page-item");
  var parent = item.parentNode;

  item.remove();

  [...Array(10).keys()].forEach(() => {
    parent.appendChild(item.cloneNode(true));
  });
})();
