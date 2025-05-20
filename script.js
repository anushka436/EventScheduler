const events = [];

const form = document.getElementById('eventForm');
const startInput = document.getElementById('startTime');
const endInput = document.getElementById('endTime');

form.addEventListener('submit', (e) => {
  e.preventDefault();

  const start = parseInt(startInput.value, 10);
  const end = parseInt(endInput.value, 10);

  if (start >= end) {
    alert('End time must be greater than start time.');
    return;
  }
  if (start < 0 || end > 24) {
    alert('Please enter start and end times between 0 and 24.');
    return;
  }

  events.push({ start, end });
  startInput.value = '';
  endInput.value = '';
  scheduleEvents();
});

function scheduleEvents() {
  // Sort by end time
  const sorted = [...events].sort((a, b) => a.end - b.end);

  const venue1 = [];
  const venue2 = [];
  const venue3 = [];

  let end1 = 0, end2 = 0, end3 = 0;

  // Clear previous lists
  document.getElementById('venue1List').innerHTML = '';
  document.getElementById('venue2List').innerHTML = '';
  document.getElementById('venue3List').innerHTML = '';

  sorted.forEach(e => {
    if (e.start >= end1) {
      venue1.push(e);
      end1 = e.end;
    } else if (e.start >= end2) {
      venue2.push(e);
      end2 = e.end;
    } else if (e.start >= end3) {
      venue3.push(e);
      end3 = e.end;
    } else {
      // Optional: alert or handle events that can't be scheduled anywhere
      alert(`Event (${e.start}, ${e.end}) could not be scheduled.`);
    }
  });

  // Populate venue lists
  venue1.forEach(e => {
    document.getElementById('venue1List').innerHTML += `<li>${e.start} - ${e.end}</li>`;
  });
  venue2.forEach(e => {
    document.getElementById('venue2List').innerHTML += `<li>${e.start} - ${e.end}</li>`;
  });
  venue3.forEach(e => {
    document.getElementById('venue3List').innerHTML += `<li>${e.start} - ${e.end}</li>`;
  });
}
