var idCounter = 0,
    Boxes = {
    /**
     * Constant for the left mouse button.
     */
    LEFT_BUTTON: 1,

    /**
     * Sets up the given jQuery collection as the drawing area(s).
     */
    setDrawingArea: function (jQueryElements) {
        jQueryElements
            .addClass("drawing-area")
            // "this" is Boxes.
            .on('mousedown', this.startDraw)
            .on('mousemove', this.trackDrag)
            .on('mouseup', this.endDrag)
            .on('mouseleave', this.endDrag);
    },

    /**
     * Utility function for disabling certain behaviors when the drawing
     * area is in certain states.
     */
    setupDragState: function () {
        $(".drawing-area .box")
            .unbind("mousemove")
            .unbind("mouseleave");
    },

    /**
     * Disables all functionality of Boxes and prepends 'inactive' to associated
     * classes.
     */
    turnOff: function () {
        $('.drawing-area')
            .addClass('inactive-drawing-area')
            .removeClass('drawing-area')
            .attr('id', 'inactive-drawing-area');
        $('.box')
            .addClass('inactive-box')
            .removeClass('box-highlight')
            .removeClass('box');
        $('.inactive-drawing-area').off();
        $('.inactive-box').off();
    },

    /**
     * Enables all functionality of Boxes and (if applicable) removes 'inactive'
     * that may have been prepended to associated classes.
     */
    turnOn: function () {
        $('.inactive-drawing-area')
            .addClass('drawing-area')
            .removeClass('inactive-drawing-area')
            .attr('id', 'drawing-area')
            .on('mousedown', this.startDraw)
            .on('mousemove', this.trackDrag)
            .on('mouseup', this.endDrag)
            .on('mouseleave', this.endDrag);
        $('.inactive-box')
            .addClass('box')
            .addClass('box-highlight')
            .removeClass('inactive-box')
            .on('mousemove', Boxes.highlight)
            .on('mousemove', Boxes.cursorChange)
            .on('mouseleave', Boxes.unhighlight)
            .on('mousedown', Boxes.startMoveOrResize);
    },

    /**
     * Begins a box draw sequence.
     */
    startDraw: function (event) {
        if (event.which === Boxes.LEFT_BUTTON) {
            // Add a new box to the drawing area.
            this.anchorX = event.pageX;
            this.anchorY = event.pageY;
            this.drawingBox = $("<div></div>")
                .appendTo(this)
                .addClass("box")
                .attr('id', 'box' + idCounter)
                .offset({ left: this.anchorX, top: this.anchorY });
            idCounter += 1;
            Boxes.setupDragState();
        }
    },

    /**
     * Tracks a box as it is rubberbanded or moved across the drawing area.
     */
    trackDrag: function (event) {
        // Don't bother if we aren't tracking anything.
        if (this.drawingBox) {
            // Calculate the new box location and dimensions.  Note how
            // this might require a "corner switch."
            var newOffset = {
                left: (this.anchorX < event.pageX) ? this.anchorX : event.pageX,
                top: (this.anchorY < event.pageY) ? this.anchorY : event.pageY
            };

            this.drawingBox
                .offset(newOffset)
                .width(Math.abs(event.pageX - this.anchorX))
                .height(Math.abs(event.pageY - this.anchorY));
        } else if (this.movingBox) {
            this.movingBox
                .mousemove(Boxes.cursorChange);

            // Resize from the top left corner
            if ((this.boxLeftSide.minimum - 15 < this.anchorX) &&
                (this.anchorX < this.boxLeftSide.maximum + 15) &&
                (this.boxTopSide.minimum - 15 < this.anchorY) &&
                (this.anchorY < this.boxTopSide.maximum + 15)) {
                this.movingBox
                    .offset({
                        left: event.pageX,
                        top: event.pageY
                     })
                    .width(this.originalSize.width + this.anchorX - event.pageX)
                    .height(this.originalSize.height + this.anchorY - event.pageY);
            }
            // Resize from the bottom left corner
            else if((this.boxLeftSide.minimum - 15 < this.anchorX) &&
                (this.anchorX < this.boxLeftSide.maximum + 15) &&
                (this.boxBottomSide.minimum - 15 < this.anchorY) &&
                (this.anchorY < this.boxBottomSide.maximum + 15)){
                this.movingBox
                    .offset({
                        left: event.pageX
                     })
                    .width(this.originalSize.width + this.anchorX - event.pageX)
                    .height(this.originalSize.height + event.pageY - this.anchorY);
            }
            // Resize from the top right corner
            else if((this.boxRightSide.minimum - 15 < this.anchorX) &&
                (this.anchorX < this.boxRightSide.maximum + 15) &&
                (this.boxTopSide.minimum - 15 < this.anchorY) &&
                (this.anchorY < this.boxTopSide.maximum + 15)){
                this.movingBox
                    .offset({
                        top: event.pageY
                     })
                    .width(this.originalSize.width + event.pageX - this.anchorX)
                    .height(this.originalSize.height + this.anchorY - event.pageY);
            }
            // Resize from the bottom right corner
            else if((this.boxRightSide.minimum - 15 < this.anchorX) &&
                (this.anchorX < this.boxRightSide.maximum + 15) &&
                (this.boxBottomSide.minimum - 15 < this.anchorY) &&
                (this.anchorY < this.boxBottomSide.maximum + 15)){
                this.movingBox
                    .width(this.originalSize.width + event.pageX - this.anchorX)
                    .height(this.originalSize.height + event.pageY - this.anchorY);
            }
            // Resize from the left side
            else if((this.boxLeftSide.minimum < this.anchorX) &&
                (this.anchorX < this.boxLeftSide.maximum)){
                this.movingBox
                    .offset({
                        left: event.pageX
                     })
                    .width(this.originalSize.width + this.anchorX - event.pageX);
            }
            // Resize from the right side
            else if((this.boxRightSide.minimum - 15 < this.anchorX) &&
                (this.anchorX < this.boxRightSide.maximum + 15)){
                this.movingBox.width(this.originalSize.width + event.pageX - this.anchorX);
            }
            // Resize from the top side
            else if((this.boxTopSide.minimum - 15 < this.anchorY) &&
                (this.anchorY < this.boxTopSide.maximum + 15)){
                this.movingBox
                    .offset({
                        top: event.pageY
                     })
                    .height(this.originalSize.height + this.anchorY - event.pageY);
            }
            // Resize from the bottom side
            else if ((this.boxBottomSide.minimum - 15 < this.anchorY) &&
                (this.anchorY < this.boxBottomSide.maximum + 15)) {
                this.movingBox.height(this.originalSize.height + event.pageY - this.anchorY);
            }
            else{
                // Reposition the object.
                this.movingBox.offset({
                    left: event.pageX - this.deltaX,
                    top: event.pageY - this.deltaY
                });

                if (event.pageX < this.drawingAreaEdges.left ||
                    event.pageX > this.drawingAreaEdges.right ||
                    event.pageY < this.drawingAreaEdges.top ||
                    event.pageY > this.drawingAreaEdges.bottom) {
                    this.movingBox.addClass('deleteState');
                    this.movingBox.text("Release to delete");
                    this.deleteState = true;
                } else {
                    this.movingBox.text("");
                    this.movingBox.removeClass('deleteState');
                    this.deleteState = false;
                }
            }
        }
    },

    /**
     * Concludes a drawing or moving sequence.
     */
    endDrag: function (event) {
        if (this.drawingBox) {
            // Finalize things by setting the box's behavior.
            this.drawingBox
                .on('mousemove', Boxes.highlight)
                .on('mousemove', Boxes.cursorChange)
                .on('mouseleave', Boxes.unhighlight)
                .on('mousedown', Boxes.startMoveOrResize);
            this.drawingBox = null;
        } else if (this.movingBox) {
            if (this.deleteState) {
                this.movingBox.remove();
            }

            // Re-populate children that were removed in the move
            this.movingBox.html(this.savedChildren);
            // Finish by clearning movingBox for next time
            this.movingBox = null;
        }

        // Restore the highlight behavior.
        $(".drawing-area .box")
            .removeClass("box-highlight")
            .mousemove(Boxes.highlight)
            .mouseleave(Boxes.unhighlight);
    },

    cursorChange: function (event) {
        var jThis = $(this),
            originalSize = {
                    width: parseInt(jThis.css('width')),
                    height: parseInt(jThis.css('height'))
                },

            originalLocation = {
                    left: parseInt(jThis.css('left')),
                    top: parseInt(jThis.css('top')),
                    right: parseInt(jThis.css('left')) + originalSize.width,
                    bottom: parseInt(jThis.css('top')) + originalSize.height
                };

        // Change cursor for top left corner
        if ((originalLocation.left - 15 < event.pageX) &&
            (event.pageX < originalLocation.left + 15) &&
            (originalLocation.top - 15 < event.pageY) &&
            (event.pageY < originalLocation.top + 15)){
            $(this).css("cursor", "nw-resize");
        }
        // Change cursor for bottom left corner
        else if ((originalLocation.left - 15 < event.pageX) &&
            (event.pageX < originalLocation.left + 15) &&
            (originalLocation.bottom - 15 < event.pageY) &&
            (event.pageY < originalLocation.bottom + 15)){
            $(this).css("cursor", "sw-resize");
        }
        // Change cursor for top right corner
        else if((originalLocation.right - 15 < event.pageX) &&
            (event.pageX < originalLocation.right + 15) &&
            (originalLocation.top - 15 < event.pageY) &&
            (event.pageY < originalLocation.top + 15)){
            $(this).css("cursor", "ne-resize");
        }
        // Change cursor for bottom right corner
        else if((originalLocation.right - 15 < event.pageX) &&
            (event.pageX < originalLocation.right + 15) &&
            (originalLocation.bottom - 15 < event.pageY) &&
            (event.pageY < originalLocation.bottom + 15)){
            $(this).css("cursor", "se-resize");
        }
        // Change cursor for left side
        else if((originalLocation.left - 15 < event.pageX) &&
            (event.pageX < originalLocation.left + 15)){
            $(this).css("cursor", "w-resize");
        }
        // Change cursor for right side
        else if((originalLocation.right - 15 < event.pageX) &&
            (event.pageX < originalLocation.right + 15)){
            $(this).css("cursor", "e-resize");
        }
        // Change cursor for top side
        else if((originalLocation.top - 15 < event.pageY) &&
            (event.pageY < originalLocation.top + 15)){
            $(this).css("cursor", "n-resize");
        }
        // Change cursor for bottom side
        else if((originalLocation.bottom - 15 < event.pageY) &&
            (event.pageY < originalLocation.bottom + 15)){
            $(this).css("cursor", "s-resize");
        }
        else{
            $(this).css("cursor", "move");
        }
    },

    /**
     * Indicates that an element is highlighted.
     */
    highlight: function () {
        $(this).addClass("box-highlight");
    },

    /**
     * Indicates that an element is unhighlighted.
     */
    unhighlight: function () {
        $(this).removeClass("box-highlight");
    },

    /**
     * Begins a box move sequence.
     */
    startMoveOrResize: function (event) {
        // We only move using the left mouse button.
        if (event.which === Boxes.LEFT_BUTTON) {
            // Take note of the box's current (global) location.
            var jThis = $(this),
                startOffset = jThis.offset(),

                // Grab the drawing area (this element's parent & not the jQuery wrapper).
                parent = jThis.parent().get(0),
                drawingAreaLeft = parseInt($('#drawing-area').css('margin-left')),
                drawingAreaTop = parseInt($('#call-to-action').css('margin-top')) +
                        parseInt($('#editor-head').css('height'));
            parent.drawingAreaEdges = {left: drawingAreaLeft,
                right: drawingAreaLeft + parseInt($('#drawing-area').css('width')),
                top: drawingAreaTop,
                bottom: drawingAreaTop + parseInt($('#drawing-area').css('height'))};

            parent.savedChildren = $('p', this);
            parent.originalSize = {width: parseInt(jThis.css('width')),
                height: parseInt(jThis.css('height'))};
            parent.originalLocation = {left: parseInt(jThis.css('left')),
                top: parseInt(jThis.css('top')),
                right: parseInt(jThis.css('left')) + parent.originalSize.width,
                bottom: parseInt(jThis.css('top')) + parent.originalSize.height
            };

            // For conditional purposes in trackDrag
            parent.boxLeftSide = {
                    minimum: parent.originalLocation.left - 15,
                    maximum: parent.originalLocation.left + 15
                };
            parent.boxRightSide = {minimum: parent.originalLocation.right - 15, maximum: parent.originalLocation.right + 15};
            parent.boxTopSide = {minimum: parent.originalLocation.top - 15, maximum: parent.originalLocation.top + 15};
            parent.boxBottomSide = {minimum: parent.originalLocation.bottom - 15, maximum: parent.originalLocation.bottom + 15};
            parent.anchorX = event.pageX;
            parent.anchorY = event.pageY;

            // Set drawing area's state to indicate that it is in a move.
            parent.movingBox = jThis;
            parent.deltaX = event.pageX - startOffset.left;
            parent.deltaY = event.pageY - startOffset.top;

            // Take away the highlight behavior
            Boxes.setupDragState();

            // Eat up the event so that the drawing area does not deal with it.
            event.stopPropagation();
        }
    }

};
