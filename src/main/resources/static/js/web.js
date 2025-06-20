(function() {
    // This function will be responsible for initializing any modal loaded onto the page.
    const initializeModal = (modalContainer) => {
        if (!modalContainer) return;

        const backdrop = modalContainer.querySelector('.modal-backdrop');
        if (!backdrop) return;

        // --- Show Modal ---
        // Use a short timeout to ensure the element is in the DOM before adding the
        // 'show' class, allowing the CSS transition to play properly.
        requestAnimationFrame(() => {
            backdrop.classList.add('show');
            document.body.classList.add('modal-open');
        });

        // --- Close Modal Function ---
        const closeModal = () => {
            backdrop.classList.remove('show');
            document.body.classList.remove('modal-open');

            // Listen for the transition to end before removing the element from the DOM.
            backdrop.addEventListener('transitionend', () => {
                // htmx.find(modalContainer) is safer than just modalContainer
                const container = htmx.find(modalContainer);
                if (container) {
                    container.innerHTML = '';
                }
            }, { once: true }); // Important: only run once
        };

        // --- Event Listeners for Closing ---
        // Use event delegation on the backdrop for closing actions.
        backdrop.addEventListener('click', (e) => {
            // Close if the backdrop itself or an element with [data-close-modal] is clicked
            if (e.target === backdrop || e.target.closest('[data-close-modal]')) {
                e.preventDefault();
                closeModal();
            }
        });

        const handleEscape = (e) => {
            if (e.key === 'Escape') {
                closeModal();
                document.removeEventListener('keydown', handleEscape);
            }
        };
        document.addEventListener('keydown', handleEscape);
    };

    // --- HTMX Event Listener ---
    // Listen for the 'htmx:afterSwap' event on the body.
    // This is the key to making the system generic.
    document.body.addEventListener('htmx:afterSwap', function(event) {
        // The modal container is the target of the htmx swap.
        const modalContainer = event.detail.target;

        // Check if the swapped content is intended to be a modal.
        // A good convention is to give the target containers a specific class.
        // Let's assume your targets (#project-modal-container, #experience-modal)
        // also have a class, e.g., <div id="project-modal-container" class="modal-host"></div>
        if (modalContainer.querySelector('.modal-backdrop')) {
            initializeModal(modalContainer);
        }
    });
})();