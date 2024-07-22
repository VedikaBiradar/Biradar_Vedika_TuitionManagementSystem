

// Check Eligibility, fee and classes form code

// Function to check eligibility based on age
function checkEligibility(age, callback) {
    const agePattern = /^\d+/; // Regular expression pattern to validate age input

    if (!agePattern.test(age)) {
        callback('Please enter a valid age.'); // Return error message if age is invalid
        return;
    }

    const ageValue = parseInt(age); // Parse the age input to an integer
    if (ageValue > 4 && ageValue < 10) {
        callback('Congratulations! you are Eligible'); // Return success message if age is within range
    } else {
        callback('We are sorry! This student is not eligible because their age is not within our criteria.'); // Return error message if age is out of range
    }
}

// Array of class details
const classes = [
    { name: "First Class", subjects: ["English", "Kannada", "Maths"], fee: 5000 },
    { name: "Second Class", subjects: ["English", "Kannada", "Maths"], fee: 7000 },
    { name: "Third Class", subjects: ["English", "Kannada", "Maths", "Science"], fee: 10000 },
    { name: "Fourth Class", subjects: ["English", "Kannada", "Maths", "Science", "History"], fee: 12000 }
];

// Function to populate class dropdowns
function populateClassDropdowns() {
    const classSelect = document.getElementById('classSelect'); // Get the class select element
    const subjectClassSelect = document.getElementById('subjectClassSelect'); // Get the subject class select element
    classes.forEach((cls, index) => {
        const option = document.createElement('option'); // Create a new option element
        option.value = index; // Set the option value to the class index
        option.text = cls.name; // Set the option text to the class name
        classSelect.add(option); // Add the option to the class select element
        const subjectOption = option.cloneNode(true); // Clone the option for the subject select element
        subjectClassSelect.add(subjectOption); // Add the option to the subject class select element
    });
}

// Function to calculate the fee
function calculateFee() {
    const classSelect = document.getElementById('classSelect'); // Get the class select element
    const daysSelect = document.getElementById('daysSelect'); // Get the days select element
    const selectedClass = classes[classSelect.value]; // Get the selected class details
    const daysPerWeek = parseInt(daysSelect.value); // Get the selected days per week
    const dailyFee = selectedClass.fee / 30; // Calculate the daily fee (assuming 30 days in a month)
    const totalFee = dailyFee * daysPerWeek * 4; // Calculate the total monthly fee

    document.getElementById('feeResult').innerText = `Total Monthly Fee: Rs ${totalFee.toFixed(2)}`; // Display the total fee
}

// Function to show subjects
function showSubjects() {
    const subjectClassSelect = document.getElementById('subjectClassSelect'); // Get the subject class select element
    const selectedClass = classes[subjectClassSelect.value]; // Get the selected class details
    document.getElementById('subjectsResult').innerText = `Subjects: ${selectedClass.subjects.join(', ')}`; // Display the subjects
}

// Initialize the dropdowns and event listeners on page load
window.addEventListener('load', function() {
    populateClassDropdowns(); // Populate the class dropdowns
    document.getElementById('checkEligibilityBtn').addEventListener('click', function() {
        const ageInput = document.getElementById('childAge').value; // Get the age input value
        checkEligibility(ageInput, function(result) {
            document.getElementById('eligibilityResult').innerText = result; // Display the eligibility result
        });
    });
    document.getElementById('calculateFeeBtn').addEventListener('click', calculateFee); // Add event listener for calculate fee button
    document.getElementById('showSubjectsBtn').addEventListener('click', showSubjects); // Add event listener for show subjects button
});


//adding EventListener for conact form to alert
document.addEventListener('DOMContentLoaded', function() {
    const contactForm = document.getElementById('contact-form'); // Targeting 'contact-form'

    if (contactForm) {
        contactForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const emailInput = document.getElementById('email').value;

            if (emailInput.includes('@')) {
                alert('Message sent successfully!');
            } else {
                alert('Please enter a valid email address.');
            }
        });
    }
});
